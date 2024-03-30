package com.example.movemates.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.MyMenuExercise;
import com.example.movemates.entity.User;
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
//	private final MyMenuService myMenuService;
	private final ExerciseLogService exerciseLogService;
	
	public MyMenuController(MyMenuRepository myMenuRepository, ExerciseRepository exerciseRepository, MyMenuService myMenuService, ExerciseLogService exerciseLogService) {
		this.myMenuRepository = myMenuRepository;
		this.exerciseRepository = exerciseRepository;
//		this.myMenuService = myMenuService;
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
		List<Exercise> exerciseList = exerciseRepository.findAll();
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		
		// ビュー側でjavascriptによるリストの更新をやりやすくするためにInteger型のListを準備
		// フォームに渡すためにオブジェクトのリストをIDのリストに変更
		List<Integer> myMenuExerciseIds = myMenu.getMyMenuExercises().stream()
				.map(MyMenuExercise::getExercise)
				.map(Exercise::getId)
				.collect(Collectors.toList());
		
		// 全てのエクササイズのIDを取得
	    List<Integer> allExerciseIds = exerciseRepository.findAll().stream()
	            .map(Exercise::getId)
	            .collect(Collectors.toList());
	    // 選択肢として表示するエクササイズのIDリストを作成
	    // すでに登録されているエクササイズを除外する
	    List<Integer> selectableExerciseIds = allExerciseIds.stream()
	            .filter(exerciseId -> !myMenuExerciseIds.contains(exerciseId))
	            .collect(Collectors.toList());
		
	    // ビューに渡すためのフォームのインスタンスを作成
	 	MyMenuEditForm myMenuEditForm = new MyMenuEditForm(mymenuId, myMenu.getName(), myMenuExerciseIds);
	    
		model.addAttribute("user", user);
		model.addAttribute("exerciseList", exerciseList);
		model.addAttribute("myMenu", myMenu);
		model.addAttribute("myMenuEditForm", myMenuEditForm);
		model.addAttribute("myMenuExerciseIds", myMenuExerciseIds);
		model.addAttribute("selectableExercises", selectableExerciseIds);
		
		return "mymenu/edit";
	}

	
	
	@GetMapping("/{mymenu_id}/vue-test")
	public String test(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "mymenu_id") Integer mymenuId, Model model) {
		User user = userDetailsImpl.getUser();
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
//		// すべてのexerciseのリスト
//		List<Exercise> allExercises = exerciseRepository.findAll();
//		// 全てのエクササイズのIDを取得
//		List<Integer> allExerciseIds = exerciseRepository.findAll().stream()
//				.map(Exercise::getId)
//				.collect(Collectors.toList());
//		// mymenuに含まれるexerciseのリスト
//		List<MyMenuExercise> myMenuExercises = myMenu.getMyMenuExercises();
		// mymenuに含まれるexerciseのIDリスト
		List<Integer> myMenuExerciseIds = myMenu.getMyMenuExercises().stream()
				.map(MyMenuExercise::getExercise)
				.map(Exercise::getId)
				.collect(Collectors.toList());
		
//	    // 選択肢として表示するエクササイズのIDリストを作成
//		// マイメニューに含まれないエクササイズのみを抽出
//		List<Exercise> selectableExercises = allExercises.stream()
//				.filter(exercise -> !myMenuExerciseIds.contains(exercise.getId()))
//				.collect(Collectors.toList());
//	    // すでに登録されているエクササイズを除外する
//	    List<Integer> candidateExerciseIds = allExerciseIds.stream()
//	            .filter(exerciseId -> !myMenuExerciseIds.contains(exerciseId))
//	            .collect(Collectors.toList());
		
//		// MyMenuオブジェクトのID情報をJSON形式で送る
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    try {
//	        String myMenuIdJson = objectMapper.writeValueAsString(mymenuId);
//	        model.addAttribute("myMenuIdJson", myMenuIdJson);
//	    } catch (JsonProcessingException e) {
//	        // JSON形式への変換中にエラーが発生した場合の処理
//	        e.printStackTrace();
//	    }
		
	    // ビューに渡すためのフォームのインスタンスを作成
	 	MyMenuEditForm myMenuEditForm = new MyMenuEditForm(mymenuId, myMenu.getName(), myMenuExerciseIds);
	    
		model.addAttribute("user", user);
		model.addAttribute("myMenu", myMenu);
		model.addAttribute("menuId", myMenu.getId());
//		model.addAttribute("exerciseList", allExercises);
		model.addAttribute("myMenuEditForm", myMenuEditForm);
//		model.addAttribute("myMenuExerciseIds", myMenuExerciseIds);
//		model.addAttribute("candidateExerciseIds", candidateExerciseIds);
		return "mymenu/vue-test";
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
