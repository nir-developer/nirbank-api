package org.nirbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@GetMapping("/myAccount")
	public String getMyAccount() {
		return "Here is your account from DB!";
	}
}
