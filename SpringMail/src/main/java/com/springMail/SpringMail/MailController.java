package com.springMail.SpringMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@PostMapping("/{mail}")
	public String sendMail(@PathVariable String mail , @RequestBody MailStructure mailStructure) {
		mailService.sendMail(mail, mailStructure);
		return "successfully mail sent!!";
	}
}
