package redis.manager;

import redis.manager.config.RedisConfig;
import util.property_file_processing.KeyValuePropertyFileReader;




import java.util.List;
import java.util.Set;

public class RedisConnectionManager {


    private static RedisConnectionManager redisConnectionManager;

    private RedisConnectionManager(String propertyFilePath) {
        List<RedisConfig> redisConfigs = loadConfigFrom(propertyFilePath);
    }

    private List<RedisConfig> loadConfigFrom(String propertyFilePath) {

        KeyValuePropertyFileReader keyValuePropertyFileAdapter = new KeyValuePropertyFileReader();

        keyValuePropertyFileAdapter.init(propertyFilePath);
        Set<String> sectionNames = keyValuePropertyFileAdapter.getSetSectionNameInPropertyFile();
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
