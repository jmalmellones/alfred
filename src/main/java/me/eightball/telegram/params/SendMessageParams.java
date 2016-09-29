package me.eightball.telegram.params;

/**
 * @author jmalmellones
 *
 */
public class SendMessageParams {

	/**
	 * Unique identifier for the target chat or username of the target channel
	 * (in the format @channelusername)
	 */
	private String chat_id;
	/**
	 * Text of the message to be sent
	 */
	private String text;

	public String getChat_id() {
		return chat_id;
	}

	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
