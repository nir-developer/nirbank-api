package org.nirbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

	@GetMapping("/myCards")
	public String getMyAccount() {
		return "Here are your cards from DB!";
	}
}
