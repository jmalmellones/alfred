package me.eightball.alfred;

import me.eightball.telegram.TelegramApi;
import me.eightball.telegram.beans.Update;
import me.eightball.telegram.params.GetUpdatesParams;
import me.eightball.telegram.params.SendMessageParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

public class TelegramTest {

	private static Logger logger;

	private static TelegramApi telegramApi;

	private static ConfigurationService configService;

	@BeforeClass
	public static void beforeClass() {
        logger = LogManager.getLogger(TelegramTest.class);
		telegramApi = TelegramApi.getInstance();
		configService = ConfigurationService.getInstance();
	}

	@Test
	public void testSendMessage() throws Exception {
		for (int x = 1; x <= 2; x++) {
			SendMessageParams params = new SendMessageParams();
			params.setChat_id(configService.getTelegramChatId());
			params.setText("hola " + x);
			logger.info(String.format("%s", telegramApi.sendMessage(params)));
		}
	}

	@Test
	public void testGetUpdates() throws Exception {
		GetUpdatesParams gup = new GetUpdatesParams();
		gup.setTimeout(5); // 5 segundos
		logger.debug("buscando updates");
		List<Update> updates = telegramApi.getUpdates(gup);
		updates.forEach(new Consumer<Update>() {

			@Override
			public void accept(Update update) {
				System.out.println(update);
			}

		});
	}

	@Test
	public void testGetMe() throws Exception {
		logger.info(telegramApi.getMe().toString());
	}
}
