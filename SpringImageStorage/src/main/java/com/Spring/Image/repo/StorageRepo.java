package com.Spring.Image.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Spring.Image.Entity.ImageData;

@Repository
public interface StorageRepo extends JpaRepository<ImageData, Long> {
	
	Optional<ImageData> findByName(String name);
}
