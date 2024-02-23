package com.example.movemates.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;
import com.example.movemates.repository.BodyPartRepository;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.PurposeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
	private final ExerciseRepository exerciseRepository;
	private final PurposeRepository purposeRepository;
	private final BodyPartRepository bodyPartRepository;
	
	public ExerciseController(ExerciseRepository exerciseRepository, PurposeRepository purposeRepository, BodyPartRepository bodyPartRepository) {
		this.exerciseRepository = exerciseRepository;
		this.purposeRepository = purposeRepository;
		this.bodyPartRepository = bodyPartRepository;
	}
	
	// アクティビティ検索トップページ
	@GetMapping
	public String index(Model model) {
		// 目的や体の部位から運動を探せるように
		List<Purpose> purposes = purposeRepository.findAll();
		List<BodyPart> bodyParts = bodyPartRepository.findAll();
		
		// 検索機能
		
		
		model.addAttribute("purposes", purposes);
		model.addAttribute("bodyParts", bodyParts);
		
		return "exercises/index";
	}
	
	// アクティビティ一覧（目的別）
	@GetMapping("/purposes/{purpose_id}")
	public String purposeExercisesList(Model model, @PathVariable(name = "purpose_id") Integer purposeId) {
		Purpose purpose = purposeRepository.getReferenceById(purposeId);
		
		List<Exercise> exercises = exerciseRepository.findByPurposes(purpose);
		
		model.addAttribute("exercises", exercises);
		model.addAttribute("title", purpose.getName());
		model.addAttribute("purpose", purpose);
		
		return "exercises/list";
	}
	
	// アクティビティ一覧（部位別）
	@GetMapping("/body-parts/{bodyPart_id}")
	public String bodyPartExercisesList(Model model, @PathVariable(name = "bodyPart_id") Integer bodyPartId) {
		BodyPart bodyPart = bodyPartRepository.getReferenceById(bodyPartId);
		
		List<Exercise> exercises = exerciseRepository.findByBodyParts(bodyPart);
		
		model.addAttribute("exercises", exercises);
		model.addAttribute("title", bodyPart.getName());
		model.addAttribute("bodyPart", bodyPart);
		
		return "exercises/list";
	}
	
	// アクティビティ詳細ページ
	@GetMapping("/{exercise_id}")
	public String show(Model model, @PathVariable(name = "exercise_id") Integer exerciseId, HttpServletRequest request) {
		Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
		// パンくずリストをアクセス元によって変更するための処理
		String referer = request.getHeader("referer");
		String breadcrumbLink = "";
		
		// リファラがない場合そのまま返す
		if (referer == null) {
			model.addAttribute("exercise", exercise);
            model.addAttribute("breadcrumbLink", breadcrumbLink);
            return "exercises/show";
        }
		
		String purposeId = request.getParameter("purpose_id");
		if (purposeId != null && !purposeId.isEmpty()) {
		    Purpose purpose = purposeRepository.findById(Integer.parseInt(purposeId)).orElse(null);
		    if (purpose != null) {
		        String purposeName = purpose.getName();
		        breadcrumbLink = "<li class=\"breadcrumb-item\"><a href=\"/exercises/purposes/" + purposeId + "\">" + purposeName + "</a></li>";
		    }
		}

		if (referer.contains("/exercises/body-parts")) {
		    String bodyPartId = request.getParameter("bodyPart_id");
		    BodyPart bodyPart = bodyPartRepository.findById(Integer.parseInt(bodyPartId)).orElse(null);
		    if (bodyPart != null) {
		        String bodyPartName = bodyPart.getName();
		        breadcrumbLink = "<li class=\"breadcrumb-item\"><a href=\"/exercises/body-parts/" + bodyPartId + "\">" + bodyPartName + "</a></li>";
		    }
		}
		
		model.addAttribute("exercise", exercise);
		model.addAttribute("breadcrumbLink", breadcrumbLink);
        return "exercises/show";
	}
}
