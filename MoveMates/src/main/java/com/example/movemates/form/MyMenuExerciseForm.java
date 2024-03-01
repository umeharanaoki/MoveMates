package com.example.movemates.form;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;

import lombok.Data;

@Data
public class MyMenuExerciseForm {
	
	private Integer id;

	private MyMenu myMenu;
	
	private Exercise exercise;
	
	private Integer exerciseOrder;
}
