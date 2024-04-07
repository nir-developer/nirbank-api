package org.nirbank.controller;

import org.nirbank.CustomerRepository;
import org.nirbank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	
	@Autowired
	private CustomerRepository customerRepository; 
	
	//FOR HASHING THE NEW RAW PASSWORD OF THE NEW USER
	@Autowired 
	private PasswordEncoder passwordEncoder; 
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer)
	{
		Customer savedCustomer = null;
		ResponseEntity response = null; 
		
		
		System.out.println("customer");
		try 
		{
			//HASH THE RAW PASSWORD
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			
			//UPDATE THE customer IN THE REQUEST BODY - SET THE HASH PASSWORD INSTEAD OF THE RAW PASSWORD 
			customer.setPwd(hashPwd);
			savedCustomer = customerRepository.save(customer);
			
			System.err.println("saved customer with hashed password"); 
			System.out.println(savedCustomer);
			
			
			//OK
			//System.err.println("POST /register control - raw password in request: " + customer.getPwd());
			//System.err.println("POST /register control - hashed password: " + hashPwd);
			
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
