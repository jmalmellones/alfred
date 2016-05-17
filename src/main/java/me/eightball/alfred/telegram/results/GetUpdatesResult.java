package me.eightball.alfred.telegram.results;

import java.util.List;

import me.eightball.alfred.telegram.beans.Update;

/**
 * Created by jmalmellones on 15/5/16.
 */
public class GetUpdatesResult extends Result {

    private List<Update> result;

	public List<Update> getResult() {
		return result;
	}

	public void setResult(List<Update> result) {
		this.result = result;
	}
    
}
