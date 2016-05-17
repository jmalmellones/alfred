package me.eightball.alfred;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
