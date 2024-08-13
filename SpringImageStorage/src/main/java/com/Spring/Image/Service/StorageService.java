package com.Spring.Image.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Spring.Image.Entity.ImageData;
import com.Spring.Image.repo.StorageRepo;

@Service
public class StorageService {
	
	 	@Autowired
	    private StorageRepo repository;
	 	
	 	private final String FOLDER_PATH = "C:/FilesForSpringBoot/";

	    public String uploadImage(MultipartFile file) throws IOException {
	    	
	    	String filePath = FOLDER_PATH+file.getOriginalFilename();
	    	
	        ImageData imageData = repository.save(ImageData.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .filePath(filePath).build());
	        
	        file.transferTo(new File(filePath));
	        
	        if (imageData != null) {
	            return "file uploaded successfully : " + filePath;
	        }
	        return null;
	    }

	    public byte[] downloadImage(String fileName) throws IOException{
	        Optional<ImageData> filedata = repository.findByName(fileName);
	        String filePath= filedata.get().getFilePath();
	        byte[] images= Files.readAllBytes(new File(filePath).toPath());
	        return images;
	    }
}
