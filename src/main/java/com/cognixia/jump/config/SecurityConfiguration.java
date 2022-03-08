package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// this class will detail how spring security is going to handle authorization and authentication
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	// handle the Authentication( who are you?)
	// lookup if the credentials ( username and password) passed through the request match any of the 
	// users for this service
	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthentication()
//			.withUser("user1")
//			.password("{noop}pw123") //{noop} -> not a part of password, stops encoding
//			.roles("USER")
//			.and()
//			.withUser("admin1")
//			.password("{noop}pw123")
//			.roles("ADMIN");
		
		// do the authorizations through the user details service, this will load the user from the database instead of in memory
		auth.userDetailsService(userDetailsService);
		
	}
	
	// Authorization ( what do you want)
	// which user have access to which uris (APIs)
	@Override
	protected void configure( HttpSecurity http) throws Exception{
		
		// more specific authorization on top and 
		// broader specification on the bottom
		
		// if creating an ADMIN type user, makes sure to put them in every antMatcher(), so they get access
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers( HttpMethod.POST, "/api/users").permitAll()
			.antMatchers( HttpMethod.GET, "/api/users").permitAll()
			.antMatchers("/api/hello").hasAnyRole("USER","ADMIN")
			.antMatchers("/**").hasRole("ADMIN")
			.and().httpBasic();
	}
	
	// mainly used to decode passwords
	@Bean
	public PasswordEncoder passwordEncoder() {
		// when password is going to be encoded and decoded, don't user any encode, the password should be clean
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		// create no password encoder, old way of setting it up, still works just deprecated
		//return NoOpPasswordEncoder.getInstance();
		
		// BCrypt encoder to do proper encoding ( very simple to set up and can use others in a similar way)
		return new BCryptPasswordEncoder();
	}
	
	
}