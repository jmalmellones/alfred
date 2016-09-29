package me.eightball.alfred;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import akka.actor.ActorSystem;

public class App
// implements CommandLineRunner
{

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		initUnirest();
		ActorSystem system = ActorSystem.create("AkkaJavaSpring");

	}

	private static void initUnirest() {
		logger.info("Initializing Unirest jacksonObjectMapper");
		Unirest.setObjectMapper(new ObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return jacksonObjectMapper.readValue(value, valueType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return jacksonObjectMapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}
