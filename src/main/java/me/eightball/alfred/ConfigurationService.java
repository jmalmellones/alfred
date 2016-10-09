package me.eightball.alfred;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationService {

	private Logger logger;

	private interface CONFIG_KEYS {
		String CHAT_ID = "telegram.chat.id";
		String TELEGRAM_TOKEN = "telegram.token";
	}

	private static final String CONFIG_FILENAME = "application.properties";

	private static ConfigurationService instance = null;

	private Properties properties;

	private ConfigurationService() {
		// you cant get an instance
		logger = LoggerFactory.getLogger(this.getClass().getName());
		properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILENAME));
		} catch (FileNotFoundException e) {
			logger.error(String.format("Could not find config file '%s'", CONFIG_FILENAME), e);
		} catch (IOException e) {
			logger.error(String.format("Error reading config file '%s'", CONFIG_FILENAME), e);
		}
	}

	public static ConfigurationService getInstance() {
		if (instance == null) {
			instance = new ConfigurationService();
		}
		return instance;
	}

	public String getTelegramChatId() {
		return properties.getProperty(CONFIG_KEYS.CHAT_ID);
	}

	public String getTelegramToken() {
		return properties.getProperty(CONFIG_KEYS.TELEGRAM_TOKEN);
	}

}
