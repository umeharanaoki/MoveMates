package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.BodyPart;

public interface BodyPartRepository extends JpaRepository<BodyPart, Integer> {
	public BodyPart findByName(String name);
}
