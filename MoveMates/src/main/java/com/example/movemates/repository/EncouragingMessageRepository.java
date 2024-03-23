package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.EncouragingMessage;

public interface EncouragingMessageRepository extends JpaRepository<EncouragingMessage, Integer> {
	EncouragingMessage findFirstByConsecutiveWeeksLessThanEqualOrderByConsecutiveWeeksDesc(Integer numberOfExerciseWeek);
}
