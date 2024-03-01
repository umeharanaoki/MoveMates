package com.example.movemates.form;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MyMenuEditForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "メニュー名を入力してください")
	private String name;
	
	private List<MyMenuExerciseForm> menuExerciseForms;
}
