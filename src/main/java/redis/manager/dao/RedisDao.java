package redis.manager.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.manager.RedisConnectionManager;

import java.util.ArrayList;
import java.util.List;

public class RedisDao {

    private RedisConnectionManager redisConnectionManager;

    public RedisDao() {
        redisConnectionManager = RedisConnectionManager.getInstance();
    }

    public Jedis getConnectionFromPool(String poolName) {
        return redisConnectionManager.getConnectionFromPool(poolName);
    }

    public Jedis getConnection() {
        Jedis connection =  redisConnectionManager.getConnectionFromPool();
        return connection;
    }

    public void closeAndReleaseConnection(Jedis connection) {
        redisConnectionManager.closeAndRelease(connection);
    }

    public String ping() {
        String response = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            response = connection.ping();
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return response;
    }

    public String get(String key) {
        String response = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            response = connection.get(key);
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return response;
    }

    public Long lPush(String key, String value) {
        Long numberInsideListAfterThisOperation = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            if (value != null) {
                numberInsideListAfterThisOperation = connection.lpush(key, value);
            }
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return numberInsideListAfterThisOperation;
    }

    public Long lPush(String key, List<String> values) {
        Long numberInsideListAfterThisOperation = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            if (values != null && values.size() > 0) {
                numberInsideListAfterThisOperation = connection.lpush(key, values.toArray(new String[values.size()]));
            }
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return numberInsideListAfterThisOperation;
    }

    public Long rPush(String key, String value) {
        Long numberInsideListAfterThisOperation = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            if (value != null) {
                numberInsideListAfterThisOperation = connection.rpush(key, value);
            }
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return numberInsideListAfterThisOperation;
    }

    public Long rPush(String key, List<String> values) {
        Long numberInsideListAfterThisOperation = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            if (values != null && values.size() > 0) {
                numberInsideListAfterThisOperation = connection.rpush(key, values.toArray(new String[values.size()]));
            }
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return numberInsideListAfterThisOperation;
    }

    public String lPop(String key) {
        String response = null;
        Jedis connection = null;

        try {
            connection = getConnection();
            if (key != null && !key.isEmpty()) {
                response = connection.lpop(key);
            }
        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return response;
    }

    public List<String> lPop(String key, int batchSize) {
        String response = null;
        Jedis connection = null;
        Pipeline pipeline = null;
        List<String> responses = new ArrayList<>();

        try {
            connection = getConnection();
            pipeline = connection.pipelined();

            if (key != null && !key.isEmpty()) {
                return null;
            }

            int i = 0;
            while (i < batchSize) {
                pipeline.lpop(key);
                i++;
            }

            List<Object> responseObjects = pipeline.syncAndReturnAll();
            for(Object responseObject : responseObjects) {
                if(responseObject !=null){
                    responses.add((String)responseObject);
                }
            }

        } catch (Exception exception) {

        } finally {
            closeAndReleaseConnection(connection);
        }
        return responses;
    }
}
