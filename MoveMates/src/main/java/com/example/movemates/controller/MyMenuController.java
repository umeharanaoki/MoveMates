package com.example.movemates.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.MyMenuExercise;
import com.example.movemates.entity.User;
import com.example.movemates.form.AjaxAddExercisesForm;
import com.example.movemates.form.ExerciseLogForm;
import com.example.movemates.form.MyMenuEditForm;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.MyMenuRepository;
import com.example.movemates.security.UserDetailsImpl;
import com.example.movemates.service.ExerciseLogService;
import com.example.movemates.service.MyMenuService;

@Controller
@RequestMapping("/mymenu")
public class MyMenuController {
	private final MyMenuRepository myMenuRepository;
	private final ExerciseRepository exerciseRepository;
	private final MyMenuService myMenuService;
	private final ExerciseLogService exerciseLogService;
	
	public MyMenuController(MyMenuRepository myMenuRepository, ExerciseRepository exerciseRepository, MyMenuService myMenuService, ExerciseLogService exerciseLogService) {
		this.myMenuRepository = myMenuRepository;
		this.exerciseRepository = exerciseRepository;
		this.myMenuService = myMenuService;
		this.exerciseLogService = exerciseLogService;
	}
	
	// マイメニュートップ
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		List<MyMenu> myMenus = myMenuRepository.findByUser(user);
		List<Exercise> exerciseList = exerciseRepository.findAll();
		
		
		model.addAttribute("user", user);
		model.addAttribute("myMenus", myMenus);
		model.addAttribute("exerciseList", exerciseList);
		
		return "mymenu/index";
	}
	
	// マイメニュー編集ページ
	@GetMapping("/{mymenu_id}/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "mymenu_id") Integer mymenuId, Model model) {
		User user = userDetailsImpl.getUser();
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		
		List<Integer> exerciseOrders = myMenu.getMyMenuExercises().stream()
	            .map(MyMenuExercise::getExerciseOrder)
	            .collect(Collectors.toList());
		
		
		MyMenuEditForm myMenuEditForm = new MyMenuEditForm(mymenuId, myMenu.getName(), myMenu.getMyMenuExercises(), exerciseOrders);
		
		// 選択肢として表示するエクササイズ
		// すでに登録されているものは含まないリストを作成
	    List<Integer> includedExerciseIds = myMenu.getMyMenuExercises().stream()
	    	.map(myMenuExercise -> myMenuExercise.getExercise().getId())
	    	.collect(Collectors.toList());
	    // 全てのエクササイズを取得
	    List<Exercise> allExercises = exerciseRepository.findAll();
	    // マイメニューに含まれないエクササイズのみを抽出
	    List<Exercise> selectableExercises = allExercises.stream()
	    	.filter(exercise -> !includedExerciseIds.contains(exercise.getId()))
	    	.collect(Collectors.toList());
		
		model.addAttribute("user", user);
		model.addAttribute("myMenuEditForm", myMenuEditForm);
		model.addAttribute("myMenu", myMenu);
		model.addAttribute("selectableExercises", selectableExercises);
		
		return "mymenu/edit";
	}
	
	// Ajaxでエクササイズの追加処理を行う	
	@PostMapping(value = "/{mymenu_id}/add-exercises", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MyMenu> addExercisesToMyMenu(@PathVariable(name = "mymenu_id") Integer mymenuId, @RequestBody AjaxAddExercisesForm ajaxAddExercisesForm) {
	    MyMenu myMenu = myMenuRepository.findById(mymenuId).orElse(null); // マイメニューを取得

	    if (myMenu == null) {
	        return ResponseEntity.notFound().build(); // マイメニューが見つからない場合は404エラーを返す
	    }

	    List<Integer> exerciseIds = ajaxAddExercisesForm.getExerciseIds(); // フォームからexerciseIdのリストを取得

	    List<Exercise> exercises = new ArrayList<>();
	    for (Integer exerciseId : exerciseIds) {
	        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null); // exerciseIdからExerciseを取得
	        if (exercise != null) {
	            exercises.add(exercise); // リストに追加
	        }
	    }

	    if (exercises.isEmpty()) {
	        return ResponseEntity.badRequest().build(); // リクエストに対応するExerciseが見つからない場合は400エラーを返す
	    }
	    
	    myMenuService.addExercisesToMyMenu(myMenu, exercises); // マイメニューにExerciseを追加

	    MyMenu savedMyMenu = myMenuRepository.save(myMenu); // マイメニューを保存

	    return ResponseEntity.ok(savedMyMenu); // 保存されたマイメニューを返す
	}
	
	// MyMenuEditFormの処理
//	@PostMapping("/update")
//	public String update(Model model) {
//		
//	}
	
	// 運動開始
	@GetMapping("/{mymenu_id}/start")
	public String start(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "mymenu_id") Integer mymenuId, Model model) {
		User user = userDetailsImpl.getUser();
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		ExerciseLogForm exerciseLogForm = new ExerciseLogForm(user, myMenu, null);
		
		model.addAttribute("user", user);
		model.addAttribute("myMenu", myMenu);
		model.addAttribute("exerciseLogForm", exerciseLogForm);		
		
		return "mymenu/start";
	}
	
	// 運動の記録
	@PostMapping("/record")
	public String record(@ModelAttribute ExerciseLogForm exerciseLogForm, RedirectAttributes redirectAttributes) {
		exerciseLogService.record(exerciseLogForm);
		
		redirectAttributes.addFlashAttribute("successMessage", "運動お疲れ様です！");
		
		return "redirect:/mymenu";
	}
}
