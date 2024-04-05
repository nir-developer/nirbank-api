package org.nirbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/home")
	public String homeContrller()
	{
		return "Hello From Not secured HomeContoller";
	}

}
