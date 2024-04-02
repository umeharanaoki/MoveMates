package com.example.movemates.controller;

import java.util.List;

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
	public String test(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PathVariable(name = "mymenu_id") Integer mymenuId, Model model) {
		User user = userDetailsImpl.getUser();
		MyMenu myMenu = myMenuRepository.getReferenceById(mymenuId);
		
	    // ビューに渡すためのフォームのインスタンスを作成
		// MyMenuに属するExerciseの情報はMyMenuのIDを用いてMyMenuEditControllerで取得するのでここではnull
	 	MyMenuEditForm myMenuEditForm = new MyMenuEditForm(mymenuId, myMenu.getName(), null);
	    
		model.addAttribute("user", user);
		model.addAttribute("myMenu", myMenu);
		model.addAttribute("myMenuEditForm", myMenuEditForm);
		return "mymenu/edit";
	}
	
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
