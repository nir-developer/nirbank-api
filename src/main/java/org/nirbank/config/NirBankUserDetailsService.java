package org.nirbank.config;

import java.util.ArrayList;
import java.util.List;

import org.nirbank.CustomerRepository;
import org.nirbank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NirBankUserDetailsService implements UserDetailsService {

	
	//INJECT THE CustomerRepository 
	@Autowired 
	CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String userName, password = null; 
		List<GrantedAuthority> authorities = null; 
		List<Customer> customer = customerRepository.findByEmail(username);
		
		if(customer.size() == 0) throw new UsernameNotFoundException("User details not found for the user: " + username);
		
		userName = customer.get(0).getEmail();
		password = customer.get(0).getPwd();
		authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
		
		
		//User class: SS  Implementation of SS UserDetails Interface
		return new User(username, password, authorities);
	}

}
