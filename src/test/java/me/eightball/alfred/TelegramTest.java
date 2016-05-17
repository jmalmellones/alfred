package me.eightball.alfred;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.eightball.alfred.telegram.TelegramApi;
import me.eightball.alfred.telegram.beans.Update;
import me.eightball.alfred.telegram.params.GetUpdatesParams;
import me.eightball.alfred.telegram.params.SendMessageParams;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class TelegramTest {

	private static final Logger logger = LoggerFactory.getLogger(TelegramTest.class);

	@Autowired
	private TelegramApi telegramApi;
	@Autowired
	private ConfigurationService configService;

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
		updates.forEach(x -> System.out.println(x));
	}

	@Test
	public void testGetMe() throws Exception {
		logger.info(telegramApi.getMe().toString());
	}
}
