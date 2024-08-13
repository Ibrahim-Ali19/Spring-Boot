package com.Spring.Image.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Spring.Image.Service.StorageService;

@RestController
@RequestMapping("/image")
public class Controller {

	
	@Autowired
	private StorageService service;
	
	@PostMapping
	public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) throws IOException{
		String uploadFile = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> downloadFile(@PathVariable String name) throws IOException{
		
		byte[] imageData = service.downloadImage(name);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png")).body(imageData);
	}
	
	
}
