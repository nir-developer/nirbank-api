package org.nirbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

	@GetMapping("/myLoans")
	public String getMyAccount() {
		return "Here is your loans from DB!";
	}
}
