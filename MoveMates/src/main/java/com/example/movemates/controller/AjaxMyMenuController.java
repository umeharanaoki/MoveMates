package com.example.movemates.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.service.MyMenuService;

@RestController
public class AjaxMyMenuController {
	private final MyMenuService myMenuService;
	
	AjaxMyMenuController(MyMenuService myMenuService) {
		this.myMenuService = myMenuService;
	}
	
	@PostMapping("/mymenu/{mymenu_id}/add-exercises")
	public ResponseEntity<MyMenu> addExercisesToMyMenu(Model model, @PathVariable(name = "mymenu_id") Integer mymenuId, @RequestBody List<Exercise> exercises) {
		// 送信されたエクササイズをマイメニューに追加
		MyMenu updatedMyMenu = myMenuService.addExercisesToMyMenu(mymenuId, exercises);
	    return ResponseEntity.ok(updatedMyMenu);
    }
}
