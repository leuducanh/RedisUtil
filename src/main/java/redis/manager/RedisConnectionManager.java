package redis.manager;

import redis.clients.jedis.*;
import redis.clients.jedis.util.Pool;
import redis.manager.config.RedisConfig;
import redis.manager.config.config_file_reader.MultiTypeFileRedisPropertyReader;
import util.property_file_processing.KeyValuePropertyFileReader;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// nếu dùng framework thì nên extend lại class này để dùng framework đọc file config.
public class RedisConnectionManager {

    private static final String DEFAULT_POOL_NAME_IF_NO_POOL_SPECIFIED = "redis_default";
    private static final String DEFAULT_PROPERTY_FILE_PATH_IF_NO_PATH_SPECIFIED = "../redis.properties";

    private static RedisConnectionManager redisConnectionManager;

    private static Map<String, Pool> poolNameToJedisPoolInstance;

    private RedisConnectionManager(String propertyFilePath) {
        List<RedisConfig> redisConfigs = loadConfigFrom(propertyFilePath);
        // NOTE: can change other map structure if you want.
        poolNameToJedisPoolInstance = new ConcurrentHashMap<>();
        for (RedisConfig redisConfig : redisConfigs) {
            JedisPoolConfig jedisPoolConfig = redisConfig.toJedisPoolConfig();

            String ip = redisConfig.getIp();
            int port = redisConfig.getPort();
            String pass = redisConfig.getPassword();
            if (pass.isEmpty()) pass = null;
            int dbSchemaNumber = redisConfig.getDb();
            if (dbSchemaNumber < 0) dbSchemaNumber = 0;

            Pool redisConnectionPool;
            if (redisConfig.getSentinel() == Boolean.TRUE) {
                Set<String> redisNodeIps = new HashSet<String>(Arrays.asList(ip.split(",")));
                redisConnectionPool = new JedisSentinelPool(redisConfig.getRedisMaster(), redisNodeIps,
                        jedisPoolConfig, redisConfig.getConnectionTimeout(), pass, dbSchemaNumber);
            } else {
                redisConnectionPool = new JedisPool(jedisPoolConfig, ip, port, redisConfig.getConnectionTimeout(), pass
                        , dbSchemaNumber);
            }

            poolNameToJedisPoolInstance.put(redisConfig.getName(), redisConnectionPool);
        }
    }

    private List<RedisConfig> loadConfigFrom(String propertyFilePath) {

        MultiTypeFileRedisPropertyReader multiTypeFileRedisPropertyReader = MultiTypeFileRedisPropertyReader.getInstance();

        return multiTypeFileRedisPropertyReader.loadRedisConfigFrom(propertyFilePath);
    }

    public static void init(String propertyFilePath) {
        if (redisConnectionManager == null) {
            synchronized (RedisConnectionManager.class) {
                if (redisConnectionManager == null) {
                    redisConnectionManager = new RedisConnectionManager(propertyFilePath);
                }
            }
        }
    }

    public static RedisConnectionManager getInstance() {
        if (redisConnectionManager == null) {
            synchronized (RedisConnectionManager.class) {
                if (redisConnectionManager == null) {
                    redisConnectionManager = new RedisConnectionManager(DEFAULT_PROPERTY_FILE_PATH_IF_NO_PATH_SPECIFIED);
                }
            }
        }
        return redisConnectionManager;
    }

    public Jedis getConnectionFromPool(String poolName) {
        if(poolName == null || poolName.isEmpty()) {
            return getConnectionFromDefaultPool();
        }

        return (Jedis) poolNameToJedisPoolInstance.get(poolName).getResource();
    }

    public Jedis getConnectionFromPool() {
        return getConnectionFromDefaultPool();
    }

    public Jedis getConnectionFromDefaultPool(){
        return (Jedis) poolNameToJedisPoolInstance.get(DEFAULT_POOL_NAME_IF_NO_POOL_SPECIFIED).getResource();
    }

    public void closeAndRelease(Jedis connection) {
        if(connection != null){
            connection.close();
        }
    }

    public String getDefaultPoolMetricInString() {
        StringBuilder infoMetric = new StringBuilder();
        Pool pool = poolNameToJedisPoolInstance.get(DEFAULT_POOL_NAME_IF_NO_POOL_SPECIFIED);

        infoMetric.append("[idle] ").append(pool.getNumIdle()).append(" , ");
        infoMetric.append("[active] ").append(pool.getNumActive()).append(" , ");
        infoMetric.append("[waiter] ").append(pool.getNumWaiters()).append(".\n");

        return infoMetric.toString();
    }

    public void printDefaultPoolMetricInStringToConsole(){
        System.out.println(redisConnectionManager.getDefaultPoolMetricInString());
    }
}
