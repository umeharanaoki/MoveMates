package com.example.movemates.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.User;
import com.example.movemates.repository.MyMenuRepository;
import com.example.movemates.repository.UserRepository;
import com.example.movemates.security.UserDetailsImpl;

@Controller
@RequestMapping("/mymenu")
public class MyMenuController {
	private final MyMenuRepository myMenuRepository;
	private final UserRepository userRepository;
	
	public MyMenuController(MyMenuRepository myMenuRepository, UserRepository userRepository) {
		this.myMenuRepository = myMenuRepository;
		this.userRepository = userRepository;
	}
	
	// マイメニュートップ
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		List<MyMenu> myMenus = myMenuRepository.findByUser(userDetailsImpl.getUser());
		
		model.addAttribute("user", user);
		model.addAttribute("myMenus", myMenus);
		
		return "mymenu/index";
	}
	
	// マイメニュー編集ページ
	@GetMapping("{mymenu_id}/edit")
	public String edit(Model model) {
		
		return "mymenu/edit";
	}
	
	// マイメニュー更新
	@PostMapping("/update")
	public void update() {
		
	}
	
	// 運動開始
	@GetMapping("{mymenu_id}/start")
	public String start(Model model) {
		return "mymenu/start";
	}
}
