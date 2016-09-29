package me.eightball.telegram.beans;

public class InlineKeyboardButton extends TelegramBean {
	/**
	 * Label text on the button
	 */
	private String text;
	/**
	 * Optional. HTTP url to be opened when button is pressed
	 */
	private String url;

	/**
	 * Optional. Data to be sent in a callback query to the bot when button is
	 * pressed, 1-64 bytes
	 */
	private String callback_data;

	/**
	 * Optional. If set, pressing the button will prompt the user to select one
	 * of their chats, open that chat and insert the bot‘s username and the
	 * specified inline query in the input field. Can be empty, in which case
	 * just the bot’s username will be inserted.
	 * 
	 * Note: This offers an easy way for users to start using your bot in inline
	 * mode when they are currently in a private chat with it. Especially useful
	 * when combined with switch_pm… actions – in this case the user will be
	 * automatically returned to the chat they switched from, skipping the chat
	 * selection screen.
	 */
	private String switch_inline_query;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCallback_data() {
		return callback_data;
	}

	public void setCallback_data(String callback_data) {
		this.callback_data = callback_data;
	}

	public String getSwitch_inline_query() {
		return switch_inline_query;
	}

	public void setSwitch_inline_query(String switch_inline_query) {
		this.switch_inline_query = switch_inline_query;
	}

}
