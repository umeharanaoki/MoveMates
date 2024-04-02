package com.example.movemates.form;

import java.util.List;

import com.example.movemates.dto.ExerciseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyMenuEditForm {
	@NotNull
	private Integer myMenuId;
	
	@NotBlank	
	private String myMenuName;
	
	// フロントではDTOクラスとして扱っていたので受け取る際はそれに合わせる
	private List<ExerciseDTO> myMenuExerciseDTOs;
}
