package me.eightball.telegram.results;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by jmalmellones on 15/5/16.
 */
public class Result<T> {
	private Boolean ok;

	public Boolean isOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	private T result;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
