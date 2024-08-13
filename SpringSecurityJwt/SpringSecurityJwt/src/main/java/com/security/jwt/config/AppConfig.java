package com.security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		User user = (User) User.builder()
//				.username("Ibrahim")
//				.password(passwordEncoder().encode("iak")).roles("ADMIN")
//				.build();
//		
//		User user2 = (User) User.builder()
//				.username("virat")
//				.password(passwordEncoder().encode("vk")).roles("ADMIN")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(user, user2);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
}
