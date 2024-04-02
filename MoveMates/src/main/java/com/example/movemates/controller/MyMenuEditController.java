package com.example.movemates.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movemates.dto.ExerciseDTO;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.MyMenuExercise;
import com.example.movemates.form.MyMenuEditForm;
import com.example.movemates.mapper.ExerciseMapper;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.MyMenuRepository;
import com.example.movemates.service.MyMenuService;

@RestController
@RequestMapping("/api")
public class MyMenuEditController {
	private ExerciseRepository exerciseRepository;
	private MyMenuRepository myMenuRepository;
	private MyMenuService myMenuService;
	
	public MyMenuEditController(ExerciseRepository exerciseRepository, MyMenuRepository myMenuRepository, MyMenuService myMenuService) {
		this.exerciseRepository = exerciseRepository;
		this.myMenuRepository = myMenuRepository;
		this.myMenuService = myMenuService;
	}
	
	@GetMapping("/edit/{mymenuId}")
	public ResponseEntity<Map<String, List<ExerciseDTO>>> list(@PathVariable(name="mymenuId") Integer mymenuId) {
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		List<Exercise> allExercises = exerciseRepository.findAll();
		
		List<MyMenuExercise> myMenuExercises = myMenu.getMyMenuExercises();
		
		List<Exercise> registeredExercises = myMenuExercises.stream()
				.map(MyMenuExercise::getExercise)
				.collect(Collectors.toList());
		
		List<ExerciseDTO> registeredExerciseDTOs = new ArrayList<>();
		for(Exercise exercise : registeredExercises) {
			registeredExerciseDTOs.add(ExerciseMapper.mapToDTO(exercise));
		}
		
		List<Exercise> candidateExercises = allExercises.stream()
			    .filter(exercise -> !myMenuExercises.stream().map(MyMenuExercise::getExercise).anyMatch(exercise::equals))
			    .collect(Collectors.toList());
		
		List<ExerciseDTO> candidateExerciseDTOs = new ArrayList<>();
		for(Exercise exercise : candidateExercises) {
			candidateExerciseDTOs.add(ExerciseMapper.mapToDTO(exercise));
		}
		
		Map<String, List<ExerciseDTO>> response = new HashMap<>();
	    response.put("registeredExerciseDTOs", registeredExerciseDTOs);
	    response.put("candidateExerciseDTOs", candidateExerciseDTOs);
	    
	    return ResponseEntity.ok().body(response);
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody MyMenuEditForm myMenuEditForm) {
		// 成功した場合
	    // 更新処理を行う
		myMenuService.update(myMenuEditForm);
	    String successMessage = myMenuEditForm.getMyMenuName() + "を更新しました。";
	    return ResponseEntity.ok(successMessage);
	    // エラーが発生した場合は、適切なエラーレスポンスを返す
	}
}
