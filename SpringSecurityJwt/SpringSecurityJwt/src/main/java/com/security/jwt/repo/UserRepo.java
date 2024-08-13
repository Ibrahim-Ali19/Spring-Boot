package com.security.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.jwt.entity.User;

public interface UserRepo extends JpaRepository< User, String> {

	public Optional<User> findByEmail(String email);
}
