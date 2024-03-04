package com.example.movemates.form;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AjaxAddExercisesForm {

	@NotNull
	private List<Integer> exerciseIds;
	
}
