package me.eightball.telegram.unirest;

import com.google.gson.Gson;
import com.mashape.unirest.http.ObjectMapper;

public class GsonObjectMapper implements ObjectMapper {

	private Gson gson = new Gson();
	
	@Override
	public <T> T readValue(String value, Class<T> valueType) {
		return gson.fromJson(value, valueType);
	}

	@Override
	public String writeValue(Object value) {
		return gson.toJson(value);
	}

}
