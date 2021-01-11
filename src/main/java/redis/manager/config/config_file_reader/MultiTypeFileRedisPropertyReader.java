package redis.manager.config.config_file_reader;

import org.apache.commons.io.FilenameUtils;
import redis.manager.config.RedisConfig;

import java.util.List;

public class MultiTypeFileRedisPropertyReader {

//    private static MultiTypeFileReaderFactory multiTypeFileReaderFactory_Singleton;
//    private static MultiTypeFileReaderFactory multiTypeFileReaderFactory_Prototype;
//
//    private RedisKeyValueFileReader redisKeyValueFileReaderSingleton;
//    private RedisYamlFileReader redisYamlFileReaderSingleton;
//
//    private String type;
//
//    public static final String TYPE_SINGLETON = "SINGLETON";
//    public static final String TYPE_PROTOTYPE = "PROTOTYPE";
//
//    private MultiTypeFileReaderFactory(final String type) {
//        this.type = type;
//
//        if(type.equals(TYPE_SINGLETON)) {
//            redisKeyValueFileReaderSingleton = new RedisKeyValueFileReader();
//            redisYamlFileReaderSingleton = new RedisYamlFileReader();
//        }
//    }
//
//    public MultiTypeFileReaderFactory getInstanceByDesignPatternType(String designPatternType) {
//        init();
//        if(type == null || !type.equals(TYPE_PROTOTYPE) || !type.equals(TYPE_PROTOTYPE)) {
//            throw new IllegalArgumentException();
//        }
//
//        switch (type){
//            case TYPE_PROTOTYPE:
//                return multiTypeFileReaderFactory_Prototype;
//            case TYPE_SINGLETON:
//                return multiTypeFileReaderFactory_Singleton;
//
//        }
//
//        return null;
//    }
//
//    private synchronized void init() {
//        if(multiTypeFileReaderFactory_Singleton == null) {
//            synchronized (MultiTypeFileReaderFactory.class){
//                if(multiTypeFileReaderFactory_Singleton == null) {
//                    multiTypeFileReaderFactory_Singleton = new MultiTypeFileReaderFactory(TYPE_SINGLETON);
//                }
//            }
//        }
//
//        if(multiTypeFileReaderFactory_Prototype == null) {
//            synchronized (MultiTypeFileReaderFactory.class){
//                if(multiTypeFileReaderFactory_Prototype == null) {
//                    multiTypeFileReaderFactory_Prototype = new MultiTypeFileReaderFactory(TYPE_PROTOTYPE);
//                }
//            }
//        }
//    }
    private static MultiTypeFileRedisPropertyReader multiTypeFileRedisPropertyReader;

    public static MultiTypeFileRedisPropertyReader getInstance() {
        if(multiTypeFileRedisPropertyReader == null) {
            synchronized (MultiTypeFileRedisPropertyReader.class){
                if(multiTypeFileRedisPropertyReader == null) {
                    multiTypeFileRedisPropertyReader = new MultiTypeFileRedisPropertyReader();
                }
            }
        }

        return multiTypeFileRedisPropertyReader;
    }
    private MultiTypeFileRedisPropertyReader() {

    }

    public List<RedisConfig> loadRedisConfig(String propertyFilePath){

        return null;
    }

    public static void main(String[] args) {
        System.out.println(FilenameUtils.getExtension("123.456.789"));

    }
}
