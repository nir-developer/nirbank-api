package org.nirbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	
	//MY CUSTOM IMPLEMENTAION of defaultSecurityFilterChain - DSL STYLE - SECTION 02:LECTURE 19 
	//(configure private and public end points as per requirements
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/myAccount", "myBalance", "myLoans", "myCards", "/h2-console/**").authenticated()
				.requestMatchers("/contact", "/notices", "/register").permitAll())	
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		
		//FOR SENDING POST REQUESTS
		 http.csrf().disable();
	     http.headers().frameOptions().disable();
		
		return http.build();
	}
	
	

///////////////////////////////////////////////////////////////////////////
//SECTION 04: Password Management & Password Encoders 
//////////////////////////////////////////////////
	@Bean 
	public PasswordEncoder bcryptPasswordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	

	
///////////////////////////////////////////////////////////////
//SECTION 03: Defining and Managing Users
//////////////////////////////////////////////////////////////
	
/*IMPORTANT: The DataSource Type
 * When I have mysql related dependencies on the class path - and I configure the datasource in applciation.properties 
 * Than SB automatically creates the DataSource object in the app
 * => WHEN CALLING new JdbcUserDetailsManager(dataSource) 
 * 	I am telling the JdbcUserDetailsManager implementation to take the db from the dataSource
 *   
 * 
 */
//

//FOR ADDING SAMPLE DATA!
//@Autowired 
//private JdbcTemplate jdbcTemplate;
	
//@Bean 
//public UserDetailsService userDetailsService(DataSource dataSource)
//{
//	//USE THE jdbcTemplate to add data 
//	//this.jdbcTemplate.execute("INSERT  INTO `users` VALUES (1, 'happy', '12345', '1')" );
//	
//	return new JdbcUserDetailsManager(dataSource);
//}

	

//I MUST TO HAVE AN INSTANCE OF PasswordEncoder - IN ANY CASE!! 
//REPLACED BY Bcrypt password encoder on section 04
//@Bean
//public PasswordEncoder passwordEncoder()
//{
//	return NoOpPasswordEncoder.getInstance();
//}
	
	
	
	

//////////////////////////////////////////////////////
//SECTION 02:CONFIGURE THE @Bean SecurityFilterChain by providing custom rules for denying/allowing all requests etc
//////////////////////////////////////////////////
//		//MY CUSTOM IMPLEMENATION : Allow all requests(not for production!) -LECTURE 20
//		@Bean
//		SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//			http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//			http.formLogin(withDefaults());
//			http.httpBasic(withDefaults());
//			return http.build();
//		}
//		

	
//	//MY CUSTOM IMPLEMENATION : Deny all requests(not for production!) -LECTURE 20
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
//		return http.build();
//	}
//	
	
	
	//MY CUSTOM IMPLEMENTAION of defaultSecurityFilterChain - DSL STYLE - LECTURE 19 
	//(configure private and public end points as per requirements
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests
//				.requestMatchers("/myAccount", "myBalance", "myLoans", "myCards").authenticated()
//				.requestMatchers("/contact", "/notices").permitAll())	
//				.formLogin(Customizer.withDefaults())
//				.httpBasic(Customizer.withDefaults());
//		
//		return http.build();
//	}
//	
//SS defaultSecurityFilterChain -  DEFAULT IMPLEMENTATION - all end points are secured! LEC 18
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//		http.formLogin(withDefaults());
//		http.httpBasic(withDefaults());
//		return http.build();
//	}
///////////////////////////////////////////////////////////////////

}
