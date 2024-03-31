package com.example.movemates.form;

import java.util.List;

import com.example.movemates.entity.Exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyMenuEditForm {
	@NotNull
	private Integer myMenuId;
	
	@NotBlank(message = "メニュー名を入力してください。")
	private String myMenuName;
	
	private List<Exercise> myMenuExercises;
}
