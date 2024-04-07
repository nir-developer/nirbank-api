package org.nirbank.config;

import java.util.ArrayList;
import java.util.List;

import org.nirbank.CustomerRepository;
import org.nirbank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class NirBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository; 
	
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				
		//1)LOAD THE USER DETAILS FROM STORAGE SYSTEM 
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString(); 
		List<Customer> customer = customerRepository.findByEmail(username);
		
		if(customer.size() > 0 ) 
		{
			//2) COMPARE THE PASSWORDS
			if(passwordEncoder.matches(pwd, customer.get(0).getPwd()))
			{
				//SUCCESS PASSWORD! Create a new Authentication object and POPULATE it with the user details
				List<GrantedAuthority> authorities = new ArrayList<>(); 
				authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
				
//				System.err.println("AUTHENTICATED");
				
				//IMPORTANT! WHEN THIS Authentication is created - it's constructor will call it's parent setter 
				//- setAuthenticated(true) !!!! 
				return new UsernamePasswordAuthenticationToken(username, pwd ,authorities);						
			}
				
			else 
			{
				throw new BadCredentialsException("Invalid password!");
				
			}
		}
		else
		{
			throw new BadCredentialsException("No user registered with this details!"); 
		}
	}

	
	@Override
	public boolean supports(Class<?> authentication) {
		//Tell SS that I want to support UsernamePasswordToken type of Authentication -
		//LIKE THE DEFAULT OF SS DaoAuthenticationProvider use!!!(COPIED FROM SOURCE! ONE TO ONE)
		
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}


/* IMPORTANT! 
 * 			THE authenticate():method 
 * 			 THIS IS MY  BUSINESS LOGIC FOR  HOW I WANT TO AUTHENTICATE: 
		 	- How to load the user details 
		 	- Comparing the passwords 
		 	- Create a succesfull Authentication Object that contains the info for if the authentication successed or not 	 	
 * 
 * */
