package com.security.jwt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwt.JwtSecurity.JwtHelper;
import com.security.jwt.entity.JwtRequest;
import com.security.jwt.entity.JwtResponse;
import com.security.jwt.entity.User;
import com.security.jwt.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
    private JwtHelper helper;
	
	@Autowired
	private UserService userService; 

    //private Logger logger = LoggerFactory.getLogger(AuthController.class);


	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
	        try {
	            this.doAuthenticate(request.getEmail(), request.getPassword());

	            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	            String token = this.helper.generateToken(userDetails);

	            JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername()).build();

	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (BadCredentialsException e) {
	            return new ResponseEntity<>("Invalid Username or Password !!", HttpStatus.UNAUTHORIZED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("An error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    private void doAuthenticate(String email, String password) {
	        try {
	            UsernamePasswordAuthenticationToken authentication = 
	                new UsernamePasswordAuthenticationToken(email, password);
	            manager.authenticate(authentication);
	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException("Invalid Username or Password !!");
	        }
	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public ResponseEntity<String> handleBadCredentials(BadCredentialsException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	    }
	
	    @PostMapping("/create-user")
	    public User createUser(@RequestBody User user) {
	    	return userService.createUser(user);
	    }
}
