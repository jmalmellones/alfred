package me.eightball.telegram.results;

import me.eightball.telegram.beans.User;

/**
 * Created by jmalmellones on 11/5/16.
 */
public class GetMeResult extends Result {

	private User result;

	public User getResult() {
		return result;
	}

	public void setResult(User result) {
		this.result = result;
	}

}
