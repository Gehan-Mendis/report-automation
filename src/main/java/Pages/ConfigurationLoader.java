package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Infra.DataProviders;

public class ConfigurationLoader {

    private static final String DEFAULT_CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static final String APPLICATION_CONFIG_FILE_PATH = "application.properties";
    
    public static Logger logger = LogManager.getLogger(ConfigurationLoader.class);

    public static String getConfigFilePath() {
        Properties applicationProperties = loadApplicationProperties();
        return applicationProperties.getProperty("configFilePath", DEFAULT_CONFIG_FILE_PATH);
    }

    static Properties loadApplicationProperties() {
        Properties properties = new Properties();
        
      //Determine active profile
        String activeProfile = System.getProperty("activeProfile");
        System.out.println("Active Profile : " + activeProfile);

      //Load configuration based on the active profile
        String configFileName = System.getProperty("configFile");
        System.out.println("Config FileName  : " + configFileName);
        if (configFileName == null) {
        	logger.error("Please specify a valid configuration file.");
            return null;
        }
        
        try (InputStream input = ConfigurationLoader.class.getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
            	logger.error("Sorry, unable to find config.properties");
                return null;
            }
            properties.load(input);
        } catch (Exception e) {
            logger.error("Failed to load configuration", e);
        }
        
        System.out.println("Properies : " + properties.toString());
        return properties;
    }
    
    public static Properties loadConfiguration(String customConfigFilePath) {
    	System.out.println("Custom Config FilePath : " + customConfigFilePath);
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(customConfigFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception appropriately (e.g., log, throw, etc.)
        }
        return properties;
    }
}
