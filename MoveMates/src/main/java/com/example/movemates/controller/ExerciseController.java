package com.example.movemates.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
	private final ExerciseRepository exerciseRepository;
	private final PurposeRepository purposeRepository;
	private final BodyPartRepository bodyPartRepository;
	private final FavoriteRepository favoriteRepository;
	
	public ExerciseController(ExerciseRepository exerciseRepository, PurposeRepository purposeRepository, BodyPartRepository bodyPartRepository, FavoriteRepository favoriteRepository) {
		this.exerciseRepository = exerciseRepository;
		this.purposeRepository = purposeRepository;
		this.bodyPartRepository = bodyPartRepository;
		this.favoriteRepository = favoriteRepository;
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
	public String purposeExercisesList(Model model, @PathVariable(name = "purpose_id") Integer purposeId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Purpose purpose = purposeRepository.getReferenceById(purposeId);
		User user = userDetailsImpl.getUser();
		
		List<Exercise> exercises = exerciseRepository.findByPurposes(purpose);
		
		model.addAttribute("exercises", exercises);
		model.addAttribute("title", purpose.getName());
		model.addAttribute("purpose", purpose);
		model.addAttribute("user", user);
		
		return "exercises/list";
	}
	
	// アクティビティ一覧（部位別）
	@GetMapping("/body-parts/{bodyPart_id}")
	public String bodyPartExercisesList(Model model, @PathVariable(name = "bodyPart_id") Integer bodyPartId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		BodyPart bodyPart = bodyPartRepository.getReferenceById(bodyPartId);
		User user = userDetailsImpl.getUser();
		
		List<Exercise> exercises = exerciseRepository.findByBodyParts(bodyPart);
		
		model.addAttribute("exercises", exercises);
		model.addAttribute("title", bodyPart.getName());
		model.addAttribute("bodyPart", bodyPart);
		model.addAttribute("user", user);
		
		return "exercises/list";
	}
	
	// アクティビティ詳細ページ
	@GetMapping("/{exercise_id}")
	public String show(Model model, @PathVariable(name = "exercise_id") Integer exerciseId, HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
		User user = userDetailsImpl.getUser();
		Favorite favorite = favoriteRepository.findByUserAndExercise(user, exercise);
		// パンくずリストをアクセス元によって変更するための処理
//		String referer = request.getHeader("referer");
//		String breadcrumbLink = "";
		
		// リファラがない場合そのまま返す
//		if (referer == null) {
//			model.addAttribute("exercise", exercise);
//            model.addAttribute("breadcrumbLink", breadcrumbLink);
//            return "exercises/show";
//        }
		
//		String purposeId = request.getParameter("purpose_id");
//		if (purposeId != null && !purposeId.isEmpty()) {
//		    Purpose purpose = purposeRepository.findById(Integer.parseInt(purposeId)).orElse(null);
//		    if (purpose != null) {
//		        String purposeName = purpose.getName();
//		        breadcrumbLink = "<li class=\"breadcrumb-item\"><a href=\"/exercises/purposes/" + purposeId + "\">" + purposeName + "</a></li>";
//		    }
//		}
//
//		if (referer.contains("/exercises/body-parts")) {
//		    String bodyPartId = request.getParameter("bodyPart_id");
//		    BodyPart bodyPart = bodyPartRepository.findById(Integer.parseInt(bodyPartId)).orElse(null);
//		    if (bodyPart != null) {
//		        String bodyPartName = bodyPart.getName();
//		        breadcrumbLink = "<li class=\"breadcrumb-item\"><a href=\"/exercises/body-parts/" + bodyPartId + "\">" + bodyPartName + "</a></li>";
//		    }
//		}
		
		model.addAttribute("exercise", exercise);
		model.addAttribute("user", user);
		model.addAttribute("favorite", favorite);
//		model.addAttribute("breadcrumbLink", breadcrumbLink);
		
        return "exercises/show";
	}
}
