package me.eightball.alfred;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import me.eightball.exceptions.ConfigurationException;

public class ConfigurationService {

	private interface CONFIG_KEYS {
		String CHAT_ID = "telegram.chat.id";
		String TELEGRAM_TOKEN = "telegram.token";
	}

	private static final String CONFIG_FILENAME = "application.properties";

	private static ConfigurationService instance = null;

	private Properties properties;

	private ConfigurationService() throws ConfigurationException {
		// you cant get an instance
		properties = new Properties();
		InputStream configFile = this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILENAME);
		if (configFile == null) {
			throw new ConfigurationException(String.format("Could not find config file '%s'", CONFIG_FILENAME));
		}
		try {
			properties.load(configFile);
		} catch (IOException e) {
			throw new ConfigurationException(String.format("Error reading config file '%s'", CONFIG_FILENAME), e);
		}
	}

	public static ConfigurationService getInstance() throws ConfigurationException {
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
