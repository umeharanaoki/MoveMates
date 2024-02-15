package com.example.movemates.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;
import com.example.movemates.repository.BodyPartRepository;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.PurposeRepository;

@Controller
@RequestMapping("/admin/exercises")
public class AdminExerciseController {
	private final ExerciseRepository exerciseRepository;
	private final PurposeRepository purposeRepository;
//	private final ExercisePurposeRepository exercisePurposeRepository;
	private final BodyPartRepository bodyPartRepository;
//	private final ExerciseBodyPartRepository exerciseBodyPartRepository;
	
	public AdminExerciseController(ExerciseRepository exerciseRepository, PurposeRepository purposeRepository, BodyPartRepository bodyPartRepository) {
		this.exerciseRepository = exerciseRepository;
		this.purposeRepository = purposeRepository;
//		this.exercisePurposeRepository = exercisePurposeRepository;
		this.bodyPartRepository = bodyPartRepository;
//		this.exerciseBodyPartRepository = exerciseBodyPartRepository;
		
	}
	
	@GetMapping
	public String index(Model model) {
		List<Exercise> exercises = exerciseRepository.findAll();
		List<Purpose> purposes = purposeRepository.findAll();
//		List<ExercisePurpose> exercisePurposes = exercisePurposeRepository.findAll();
		List<BodyPart> bodyParts = bodyPartRepository.findAll();
//		List<ExerciseBodyPart> exerciseBodyParts = exerciseBodyPartRepository.findAll();
		
		model.addAttribute("exercises", exercises);
		model.addAttribute("purposes", purposes);
//		model.addAttribute("exercisePurposes", exercisePurposes);
		model.addAttribute("bodyParts", bodyParts);
//		model.addAttribute("exerciseBodyParts", exerciseBodyParts);
		
		return "admin/exercises/index";
	}
}
