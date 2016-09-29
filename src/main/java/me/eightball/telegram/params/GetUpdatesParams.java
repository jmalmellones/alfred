package me.eightball.telegram.params;

public class GetUpdatesParams implements Cloneable {
	private Integer offset;
	private Integer limit;
	private Integer timeout;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	@Override
	public GetUpdatesParams clone() throws CloneNotSupportedException {
		GetUpdatesParams result = new GetUpdatesParams();
		result.setLimit(limit);
		result.setOffset(offset);
		result.setTimeout(timeout);
		return result;
	}

}
