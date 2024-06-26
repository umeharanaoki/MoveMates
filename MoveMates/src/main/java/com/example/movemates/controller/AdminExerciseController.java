package com.example.movemates.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;
import com.example.movemates.form.ExerciseEditForm;
import com.example.movemates.form.ExerciseRegisterForm;
import com.example.movemates.mapper.ExerciseMapper;
import com.example.movemates.repository.BodyPartRepository;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.PurposeRepository;
import com.example.movemates.service.ExerciseService;

@Controller
@RequestMapping("/admin/exercises")
public class AdminExerciseController {
	private final ExerciseRepository exerciseRepository;
	private final PurposeRepository purposeRepository;
	private final BodyPartRepository bodyPartRepository;
	private final ExerciseService exerciseService;
	
	public AdminExerciseController(ExerciseRepository exerciseRepository, PurposeRepository purposeRepository, BodyPartRepository bodyPartRepository, ExerciseService exerciseService) {
		this.exerciseRepository = exerciseRepository;
		this.purposeRepository = purposeRepository;
		this.bodyPartRepository = bodyPartRepository;
		this.exerciseService = exerciseService;
	}
	
	// 管理者用アクティビティ一覧ページ
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
		Page<Exercise> exercisePage = exerciseService.getSearchedExercises(keyword, pageable);
		
		model.addAttribute("exercisePage", exercisePage);
		model.addAttribute("keyword", keyword);
		
		return "admin/exercises/index";
	}
	
	// 管理者用アクティビティ詳細ページ
	@GetMapping("/{exerciseId}")
	public String show(Model model, @PathVariable(name = "exerciseId") Integer exerciseId) {
		Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
		
		model.addAttribute("exercise", exercise);
		
		return "admin/exercises/show";
	}
	
	// アクティビティ登録ページ
	@GetMapping("/register")
	public String register(Model model) {
		// 選択肢を一覧表示するためのリスト
		List<Purpose> purposes = purposeRepository.findAll();
		List<BodyPart> bodyParts = bodyPartRepository.findAll();
		
		model.addAttribute("exerciseRegisterForm", new ExerciseRegisterForm());
		model.addAttribute("purposes", purposes);
		model.addAttribute("bodyParts", bodyParts);
		
		return "admin/exercises/register";
	}
	
	// 新規登録処理
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated ExerciseRegisterForm exerciseRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "admin/exercises/register";
		}
		
		exerciseService.create(exerciseRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "アクティビティを登録しました。");
		
		return "redirect:/admin/exercises";
	}
	
	// アクティビティ編集ページ
	@GetMapping("/{exerciseId}/edit")
	public String edit(Model model, @PathVariable(name = "exerciseId") Integer exerciseId) {
		// 選択肢を一覧表示するためのリスト
		List<Purpose> purposes = purposeRepository.findAll();
		List<BodyPart> bodyParts = bodyPartRepository.findAll();
		
		Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
		String imageName = exercise.getImageName();
		// オブジェクトリストをそのまま渡すとpurposeやbodyPartはexerciseと多対多の関係のためにループしておかしくなるので名前だけ渡す
		List<String> purposeNames = ExerciseMapper.mapPurposesToStrings(exercise.getPurposes());
		List<String> bodyPartNames = ExerciseMapper.mapBodyPartsToStrings(exercise.getBodyParts());
		
		ExerciseEditForm exerciseEditForm = new ExerciseEditForm(exercise.getId(), exercise.getName(), null, exercise.getType(), purposeNames, bodyPartNames, exercise.getExplanation(), exercise.getSetNumber(), exercise.getTimeRequired());
		
		model.addAttribute("exerciseEditForm", exerciseEditForm);
		model.addAttribute("imageName", imageName);
		model.addAttribute("purposes", purposes);
		model.addAttribute("bodyParts", bodyParts);
		
		return "admin/exercises/edit";
	}
	
	// 更新機能
	@PostMapping("/update")
	public String update(@ModelAttribute @Validated ExerciseEditForm exerciseEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "admin/exercises/edit";
		}
		
		exerciseService.update(exerciseEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "アクティビティを更新しました。");
		
		return "redirect:/admin/exercises";
	}
	
	// 削除機能
	@PostMapping("/{exerciseId}/delete")
	public String delete(@PathVariable(name = "exerciseId") Integer exerciseId, RedirectAttributes redirectAttributes) {
		exerciseRepository.deleteById(exerciseId);
		redirectAttributes.addFlashAttribute("successMessage", "アクティビティを削除しました。");
		
		return "redirect:/admin/exercises";
	}
}
