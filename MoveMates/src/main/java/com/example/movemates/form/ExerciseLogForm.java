package com.example.movemates.form;

import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseLogForm {
	
	private User user;
	
	private MyMenu myMenu;
	
	private Integer exerciseDuration;
}
