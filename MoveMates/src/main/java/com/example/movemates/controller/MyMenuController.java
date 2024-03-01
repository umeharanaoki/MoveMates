package com.example.movemates.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.MyMenuExercise;
import com.example.movemates.entity.User;
import com.example.movemates.form.MyMenuEditForm;
import com.example.movemates.form.MyMenuExerciseForm;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.MyMenuRepository;
import com.example.movemates.repository.UserRepository;
import com.example.movemates.security.UserDetailsImpl;

@Controller
@RequestMapping("/mymenu")
public class MyMenuController {
	private final MyMenuRepository myMenuRepository;
	private final UserRepository userRepository;
	private final ExerciseRepository exerciseRepository;
	
	public MyMenuController(MyMenuRepository myMenuRepository, UserRepository userRepository, ExerciseRepository exerciseRepository) {
		this.myMenuRepository = myMenuRepository;
		this.userRepository = userRepository;
		this.exerciseRepository = exerciseRepository;
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
	@GetMapping("{mymenu_id}/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "mymenu_id") Integer mymenuId, Model model) {
		User user = userDetailsImpl.getUser();
		// どのメニューを編集するか
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		List<Exercise> exerciseList = exerciseRepository.findAll();
		
		//
		MyMenuEditForm myMenuEditForm = new MyMenuEditForm();
		myMenuEditForm.setId(mymenuId);
		myMenuEditForm.setName(myMenu.getName());
		
		List<MyMenuExerciseForm> menuExerciseForms = new ArrayList<>();
        for (MyMenuExercise myMenuExercise : myMenu.getMyMenuExercises()) {
            MyMenuExerciseForm menuExerciseForm = new MyMenuExerciseForm();
            menuExerciseForm.setMyMenu(myMenuExercise.getMyMenu());
            menuExerciseForm.setExercise(myMenuExercise.getExercise());
            menuExerciseForm.setExerciseOrder(myMenuExercise.getExerciseOrder());
            menuExerciseForms.add(menuExerciseForm);
        }
		
        myMenuEditForm.setMenuExerciseForms(menuExerciseForms);
        
		model.addAttribute("user", user);
		model.addAttribute("myMenuEditForm", myMenuEditForm);
		model.addAttribute("exerciseList", exerciseList);
		
		return "mymenu/edit";
	}
	
	
	
	// 運動開始
	@GetMapping("{mymenu_id}/start")
	public String start(Model model) {
		return "mymenu/start";
	}
}
