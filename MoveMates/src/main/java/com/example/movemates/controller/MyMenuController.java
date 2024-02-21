package com.example.movemates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movemates.repository.MyMenuRepository;

@Controller
@RequestMapping("/mymenu")
public class MyMenuController {
	private final MyMenuRepository myMenuRepository;
	
	public MyMenuController(MyMenuRepository myMenuRepository) {
		this.myMenuRepository = myMenuRepository;
	}
	
	// マイメニュートップ
	@GetMapping
	public String index(Model model) {
		return "mymenu/index";
	}
	
	// マイメニュー登録ページ
	@GetMapping("/register")
	public String register(Model model) {
		return "mymenu/register";
	}
	
	// マイメニュー登録
	@PostMapping("/create")
	public void create() {
		
	}
	
	// マイメニュー編集ページ
	@GetMapping("{mymenu_id}/edit")
	public String edit(Model model) {
		return "mymenu/register";
	}
	
	// マイメニュー更新
	@PostMapping("/update")
	public void update() {
		
	}
	
	// 運動開始
	
}
