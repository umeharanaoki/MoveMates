package com.example.movemates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.movemates.entity.ExerciseLog;
import com.example.movemates.entity.User;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Integer>{
	
	@Query("SELECT e FROM ExerciseLog e WHERE e.user = :user")
	public List<ExerciseLog> findByUser(User user);
}
