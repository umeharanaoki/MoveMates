package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.MyMenuExercise;

public interface MyMenuExerciseRepository extends JpaRepository<MyMenuExercise, Integer>{
	void deleteByMyMenuId(Integer myMenuId);
}
