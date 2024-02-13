package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

}
