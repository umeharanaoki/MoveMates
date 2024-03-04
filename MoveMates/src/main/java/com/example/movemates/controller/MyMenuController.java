package com.example.movemates.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.User;
import com.example.movemates.form.AjaxAddExercisesForm;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.MyMenuRepository;
import com.example.movemates.repository.UserRepository;
import com.example.movemates.security.UserDetailsImpl;
import com.example.movemates.service.MyMenuService;

@Controller
@RequestMapping("/mymenu")
public class MyMenuController {
	private final MyMenuRepository myMenuRepository;
	private final UserRepository userRepository;
	private final ExerciseRepository exerciseRepository;
	private final MyMenuService myMenuService;
	
	public MyMenuController(MyMenuRepository myMenuRepository, UserRepository userRepository, ExerciseRepository exerciseRepository, MyMenuService myMenuService) {
		this.myMenuRepository = myMenuRepository;
		this.userRepository = userRepository;
		this.exerciseRepository = exerciseRepository;
		this.myMenuService = myMenuService;
	}
	
	// マイメニュートップ
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		List<MyMenu> myMenus = myMenuRepository.findByUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("myMenus", myMenus);
		
		return "mymenu/index";
	}
	
	// マイメニュー編集ページ
	@GetMapping("/{mymenu_id}/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "mymenu_id") Integer mymenuId, Model model) {
		User user = userDetailsImpl.getUser();
		// どのメニューを編集するか
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		List<Exercise> exerciseList = exerciseRepository.findAll();
		
		//
//		MyMenuEditForm myMenuEditForm = new MyMenuEditForm();
//		myMenuEditForm.setId(mymenuId);
//		myMenuEditForm.setName(myMenu.getName());
//		
//		List<MyMenuExerciseForm> menuExerciseForms = new ArrayList<>();
//        for (MyMenuExercise myMenuExercise : myMenu.getMyMenuExercises()) {
//            MyMenuExerciseForm menuExerciseForm = new MyMenuExerciseForm();
//            menuExerciseForm.setMyMenu(myMenuExercise.getMyMenu());
//            menuExerciseForm.setExercise(myMenuExercise.getExercise());
//            menuExerciseForm.setExerciseOrder(myMenuExercise.getExerciseOrder());
//            menuExerciseForms.add(menuExerciseForm);
//        }
//		
//        myMenuEditForm.setMenuExerciseForms(menuExerciseForms);
        
		model.addAttribute("user", user);
//		model.addAttribute("myMenuEditForm", myMenuEditForm);
		model.addAttribute("myMenu", myMenu);
		model.addAttribute("exerciseList", exerciseList);
		
		return "mymenu/edit";
	}
	
	// Ajaxでエクササイズの追加処理を行う	
	@PostMapping("/{mymenu_id}/add-exercises")
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

	
	
	// 運動開始
	@GetMapping("/{mymenu_id}/start")
	public String start(Model model) {
		return "mymenu/start";
	}
}
