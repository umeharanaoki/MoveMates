package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
	public VerificationToken findByToken(String token);
}
