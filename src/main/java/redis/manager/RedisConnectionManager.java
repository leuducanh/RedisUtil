package redis.manager;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.manager.config.RedisConfig;
import redis.manager.config.config_file_reader.MultiTypeFileRedisPropertyReader;
import util.property_file_processing.KeyValuePropertyFileReader;




import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// nếu dùng framework thì nên extend lại class này để dùng framework đọc file config.
public class RedisConnectionManager {


    private static RedisConnectionManager redisConnectionManager;

    private static Map<String, JedisPool> poolNameToJedisPoolInstance;

    private RedisConnectionManager(String propertyFilePath) {
        List<RedisConfig> redisConfigs = loadConfigFrom(propertyFilePath);
        // NOTE: can change other map structure if you want.
        poolNameToJedisPoolInstance = new ConcurrentHashMap<>();
    }

    private List<RedisConfig> loadConfigFrom(String propertyFilePath) {

        MultiTypeFileRedisPropertyReader multiTypeFileRedisPropertyReader = MultiTypeFileRedisPropertyReader.getInstance();

        List<RedisConfig> redisConfigs = multiTypeFileRedisPropertyReader.loadRedisConfigFrom(propertyFilePath);

        for(RedisConfig redisConfig : redisConfigs) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();




        }

        return null;
    }

    public static RedisConnectionManager getInstance(String propertyFilePath) {
        if (redisConnectionManager == null) {
            synchronized (RedisConnectionManager.class) {
                if (redisConnectionManager == null) {
                    redisConnectionManager = new RedisConnectionManager(propertyFilePath);
                }
            }
        }

        int a;
        return redisConnectionManager;
    }
}
