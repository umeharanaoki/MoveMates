package com.example.movemates.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Favorite;
import com.example.movemates.entity.Purpose;
import com.example.movemates.entity.User;
import com.example.movemates.repository.BodyPartRepository;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.FavoriteRepository;
import com.example.movemates.repository.PurposeRepository;
import com.example.movemates.security.UserDetailsImpl;
import com.example.movemates.service.ExerciseService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
	private final ExerciseRepository exerciseRepository;
	private final PurposeRepository purposeRepository;
	private final BodyPartRepository bodyPartRepository;
	private final FavoriteRepository favoriteRepository;
	private final ExerciseService exerciseService;
	
	public ExerciseController(ExerciseRepository exerciseRepository, PurposeRepository purposeRepository, BodyPartRepository bodyPartRepository, FavoriteRepository favoriteRepository, ExerciseService exerciseService) {
		this.exerciseRepository = exerciseRepository;
		this.purposeRepository = purposeRepository;
		this.bodyPartRepository = bodyPartRepository;
		this.favoriteRepository = favoriteRepository;
		this.exerciseService = exerciseService;
	}
	
	// アクティビティ検索トップページ
	@GetMapping
	public String index(Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		// 目的や体の部位から運動を探せるように
		List<Purpose> purposes = purposeRepository.findAll();
		List<BodyPart> bodyParts = bodyPartRepository.findAll();
		User user = userDetailsImpl.getUser();
		
		// 検索機能
		
		
		model.addAttribute("purposes", purposes);
		model.addAttribute("bodyParts", bodyParts);
		model.addAttribute("user", user);
		
		return "exercises/index";
	}
	
	// アクティビティ一覧（目的別）
	@GetMapping("/purposes/{purpose_id}")
	public String purposeExercisesList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @PathVariable(name = "purpose_id") Integer purposeId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Purpose purpose = purposeRepository.getReferenceById(purposeId);
		User user = userDetailsImpl.getUser();
		
		Page<Exercise> exercisePage = exerciseRepository.findByPurposes(purpose, pageable);
		
		model.addAttribute("exercisePage", exercisePage);
		model.addAttribute("title", purpose.getName());
		model.addAttribute("purpose", purpose);
		model.addAttribute("user", user);
		
		return "exercises/list";
	}
	
	// アクティビティ一覧（部位別）
	@GetMapping("/body-parts/{bodyPart_id}")
	public String bodyPartExercisesList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @PathVariable(name = "bodyPart_id") Integer bodyPartId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		BodyPart bodyPart = bodyPartRepository.getReferenceById(bodyPartId);
		User user = userDetailsImpl.getUser();
		
		Page<Exercise> exercisePage = exerciseRepository.findByBodyParts(bodyPart, pageable);
		
		model.addAttribute("exercisePage", exercisePage);
		model.addAttribute("title", bodyPart.getName());
		model.addAttribute("bodyPart", bodyPart);
		model.addAttribute("user", user);
		
		return "exercises/list";
	}
	
	// アクティビティ一覧（すべて）
	@GetMapping("/all")
	public String all(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userDetailsImpl.getUser();
		
		Page<Exercise> exercisePage = exerciseService.getSearchedExercises(keyword, pageable);
		
		model.addAttribute("exercisePage", exercisePage);
		model.addAttribute("user", user);
		model.addAttribute("keyword", keyword);
		
		return "exercises/all";
	}
	
	// アクティビティ詳細ページ
	@GetMapping("/{exercise_id}")
	public String show(Model model, @PathVariable(name = "exercise_id") Integer exerciseId, HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
		User user = userDetailsImpl.getUser();
		Favorite favorite = favoriteRepository.findByUserAndExercise(user, exercise);
		
		model.addAttribute("exercise", exercise);
		model.addAttribute("user", user);
		model.addAttribute("favorite", favorite);
		
        return "exercises/show";
	}
}
