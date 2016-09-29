package me.eightball.telegram.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inlineKeyboard")
public class InlineKeyboardController {

	private static class Result {
		public String a;
	}

	@RequestMapping("/test")
	public Result test() {
		Result a = new Result();
		a.a = "caca";
		return a;
	}
}
