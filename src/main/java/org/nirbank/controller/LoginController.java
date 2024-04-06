package org.nirbank.controller;

import org.nirbank.CustomerRepository;
import org.nirbank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	
	@Autowired
	private CustomerRepository customerRepository; 
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer)
	{
		Customer savedCustomer = null;
		ResponseEntity response = null; 
		
		
		System.out.println("customer");
		try 
		{
			savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getId()>0) 
			{
				response =ResponseEntity.status(HttpStatus.CREATED) 
				.body("Given user details successfully registered");
						
			}
		}
		catch(Exception ex)
		{
			response =ResponseEntity 
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to " + ex.getMessage());
					

		}
		
		return response; 
	}
}
