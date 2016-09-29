package me.eightball.telegram.beans;

public class CallbackQuery extends TelegramBean {
	/**
	 * Unique identifier for this query
	 */
	private String id;
	/**
	 * Sender
	 */
	private User from;

	/**
	 * Optional. Message with the callback button that originated the query.
	 * Note that message content and message date will not be available if the
	 * message is too old
	 */
	private Message message;

	/**
	 * Optional. Identifier of the message sent via the bot in inline mode, that
	 * originated the query
	 */
	private String inline_message_id;
	/**
	 * Data associated with the callback button. Be aware that a bad client can
	 * send arbitrary data in this field
	 */
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getInline_message_id() {
		return inline_message_id;
	}

	public void setInline_message_id(String inline_message_id) {
		this.inline_message_id = inline_message_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
