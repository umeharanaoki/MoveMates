package com.example.movemates.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movemates.entity.ExerciseLog;
import com.example.movemates.form.ExerciseLogForm;
import com.example.movemates.repository.ExerciseLogRepository;

@Service
public class ExerciseLogService {
	private final ExerciseLogRepository exerciseLogRepository;
	
	public ExerciseLogService(ExerciseLogRepository exerciseLogRepository) {
		this.exerciseLogRepository = exerciseLogRepository;
	}
	
	@Transactional
	public void record(ExerciseLogForm exerciseLogForm) {
		ExerciseLog exerciseLog = new ExerciseLog();
		
		exerciseLog.setUser(exerciseLogForm.getUser());
		exerciseLog.setMyMenu(exerciseLogForm.getMyMenu());
		exerciseLog.setExerciseDuration(exerciseLogForm.getExerciseDuration());
		
		exerciseLogRepository.save(exerciseLog);
	}
}
