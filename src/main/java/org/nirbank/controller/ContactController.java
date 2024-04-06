package org.nirbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	
	@GetMapping("/contact")
	public String getBalance()
	{
		return "Here are  public contact  from DB"; 
	}

}
