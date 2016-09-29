package me.eightball.telegram.exceptions;

/**
 * Created by jmalmellones on 11/5/16.
 */
public class TelegramException extends Exception {

	public TelegramException(String message) {
		super(message);
	}

	public TelegramException(String message, Throwable cause) {
		super(message, cause);
	}

	public TelegramException(Throwable cause) {
		super(cause);
	}
}
