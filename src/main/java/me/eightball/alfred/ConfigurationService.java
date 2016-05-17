package me.eightball.alfred;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

	@Value("${telegram.chat.id}")
	private String telegramChatId;
	@Value("${telegram.token}")
	private String telegramToken;

	public String getTelegramChatId() {
		return telegramChatId;
	}

	public String getTelegramToken() {
		return telegramToken;
	}

}
