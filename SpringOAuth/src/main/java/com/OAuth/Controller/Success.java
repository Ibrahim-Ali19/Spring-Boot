package com.OAuth.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Success {

	@GetMapping("/success")
	public String success() {
		
		return "Successfully logged In"; 
	}
	
}
