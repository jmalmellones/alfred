package me.eightball.telegram;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;

import me.eightball.alfred.ConfigurationService;
import me.eightball.telegram.beans.Message;
import me.eightball.telegram.beans.Update;
import me.eightball.telegram.beans.User;
import me.eightball.telegram.exceptions.TelegramException;
import me.eightball.telegram.params.GetUpdatesParams;
import me.eightball.telegram.params.SendMessageParams;
import me.eightball.telegram.results.GetMeResult;
import me.eightball.telegram.results.GetUpdatesResult;
import me.eightball.telegram.results.SendMessageResult;

/**
 * Created by jmalmellones on 11/5/16.
 */
public class TelegramApi {

	private static Logger logger;
	private static TelegramApi instance;

	public static TelegramApi getInstance() {
		if (instance == null) {
			instance = new TelegramApi();
		}
		return instance;
	}

	private ConfigurationService configurationService;

	private TelegramApi() {
		logger = LoggerFactory.getLogger(this.getClass());
		configurationService = ConfigurationService.getInstance();
	}

	private Integer lastUpdateId = null;

	public User getMe() throws TelegramException {
		try {
			HttpResponse<GetMeResult> response = Unirest
					.post("https://api.telegram.org/bot" + configurationService.getTelegramToken() + "/getMe")
					.header("accept", "application/json").asObject(GetMeResult.class);
			if (response.getBody().isOk()) {
				return response.getBody().getResult();
			} else {
				return null;
			}
		} catch (UnirestException e) {
			throw new TelegramException(e);
		}
	}

	/**
	 * @param offset
	 *            Identifier of the first update to be returned. Must be greater
	 *            by one than the highest among the identifiers of previously
	 *            received updates. By default, updates starting with the
	 *            earliest unconfirmed update are returned. An update is
	 *            considered confirmed as soon as getUpdates is called with an
	 *            offset higher than its update_id. The negative offset can be
	 *            specified to retrieve updates starting from -offset update
	 *            from the end of the updates queue. All previous updates will
	 *            forgotten.
	 * @param limit
	 *            Limits the number of updates to be retrieved. Values between
	 *            1—100 are accepted. Defaults to 100.
	 * @param timeout
	 *            Timeout in seconds for long polling. Defaults to 0, i.e. usual
	 *            short polling
	 * @return
	 * @throws TelegramException
	 */
	public List<Update> getUpdates(GetUpdatesParams params) throws TelegramException {
		// return getUpdates(lastUpdateId == null ? null : lastUpdateId + 1,
		// limit, timeout);
		HttpRequestWithBody request = Unirest
				.post("https://api.telegram.org/bot" + configurationService.getTelegramToken() + "/getUpdates")
				.header("accept", "application/json").header("Content-Type", "application/json");
		GetUpdatesParams newParams = params;
		if (newParams.getOffset() == null && lastUpdateId != null) {
			try {
				newParams = params.clone();
				newParams.setOffset(lastUpdateId + 1);
			} catch (CloneNotSupportedException e) {
				throw new TelegramException("Error clonando los parametros", e);
			}
		}
		RequestBodyEntity rbe = request.body(newParams);
		try {
			HttpResponse<GetUpdatesResult> response = rbe.asObject(GetUpdatesResult.class);
			if (response.getBody().isOk()) {
				List<Update> updates = response.getBody().getResult();
				if (!updates.isEmpty()) {
					lastUpdateId = updates.get(updates.size() - 1).getUpdate_id();
				}
				return updates;
			} else {
				return null;
			}
		} catch (UnirestException e) {
			throw new TelegramException(e);
		}
	}

	public Message sendMessage(SendMessageParams params) throws TelegramException {
		HttpRequestWithBody request = Unirest
				.post("https://api.telegram.org/bot" + configurationService.getTelegramToken() + "/sendMessage")
				.header("accept", "application/json").header("Content-Type", "application/json");
		RequestBodyEntity rbe = request.body(params);
		try {
			HttpResponse<SendMessageResult> response = rbe.asObject(SendMessageResult.class);
			if (response.getBody().isOk()) {
				return response.getBody().getResult();
			} else {
				return null;
			}
		} catch (UnirestException e) {
			throw new TelegramException(e);
		}
	}

}
