package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.Purpose;

public interface PurposeRepository extends JpaRepository<Purpose, Integer> {
	public Purpose findByName(String name);
}
