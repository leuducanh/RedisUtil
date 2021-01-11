package redis.manager.config.config_file_reader;

import org.apache.commons.io.FilenameUtils;
import org.ini4j.Profile;
import redis.manager.config.RedisConfig;
import util.clazz.ClassUtils;
import util.file.FileNameUtils;
import util.property_file_processing.KeyValuePropertyFileReader;
import util.property_file_processing.PropertyFileProcessingFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static util.file.FileNameUtils.KEY_VALUE;
import static util.file.FileNameUtils.YAML;

public class MultiTypeFileRedisPropertyReader {
    private static MultiTypeFileRedisPropertyReader multiTypeFileRedisPropertyReader;
    private PropertyFileProcessingFactory propertyFileProcessingFactory;

    public static MultiTypeFileRedisPropertyReader getInstance() {
        if (multiTypeFileRedisPropertyReader == null) {
            synchronized (MultiTypeFileRedisPropertyReader.class) {
                if (multiTypeFileRedisPropertyReader == null) {
                    multiTypeFileRedisPropertyReader = new MultiTypeFileRedisPropertyReader();
                }
            }
        }

        return multiTypeFileRedisPropertyReader;
    }

    private MultiTypeFileRedisPropertyReader() {
        propertyFileProcessingFactory = new PropertyFileProcessingFactory();
    }

    public List<RedisConfig> loadRedisConfigFrom(String propertyFilePath) {

        final String redisPropertyFileExtension = FileNameUtils.getFileExtension(propertyFilePath);

        switch (redisPropertyFileExtension) {
            case KEY_VALUE:
                return loadRedisConfigFromKeyValueFile(propertyFilePath);
            case YAML:
                return loadRedisConfigFromYamlFile(propertyFilePath);
        }

        return null;
    }

    private List<RedisConfig> loadRedisConfigFromYamlFile(String propertyFilePath) {
        propertyFileProcessingFactory.generateFileReaderBy(YAML);

        return null;
    }

    private static List<RedisConfig> loadRedisConfigFromKeyValueFile(String propertyFilePath) {
        KeyValuePropertyFileReader keyValuePropertyFileReader =
                new KeyValuePropertyFileReader();

        keyValuePropertyFileReader.init(propertyFilePath);
        Set<String> sectionNames = keyValuePropertyFileReader.getSetSectionNameInPropertyFile();

        List<RedisConfig> redisConfigs = new ArrayList<>();
        for (String sectionName : sectionNames) {
            Profile.Section section = keyValuePropertyFileReader.getSection(sectionName);
            RedisConfig redisConfig = new RedisConfig();

            Class<?> clazzRedisConfig = redisConfig.getClass();
            Field[] fields = clazzRedisConfig.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                String propertyFieldName = field.getName();
                String valueStringFromPropertyFile = keyValuePropertyFileReader
                        .readWithDefaultValue(section, propertyFieldName, null);

                Object valueAfterParse = null;
                if (valueStringFromPropertyFile != null) {
                    valueAfterParse = ClassUtils.parseStringToObjectDependOnClassType(field.getType(), valueStringFromPropertyFile);
                }

                try {
                    field.set(redisConfig, valueAfterParse);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            redisConfigs.add(redisConfig);
        }
        return redisConfigs;
    }

    public static void main(String[] args) {
        loadRedisConfigFromKeyValueFile("E:\\Git\\ttnn_peru_smsbrandname\\SOURCE_CODE\\redis_util_multidb\\etc\\redis.properties");

    }
}
