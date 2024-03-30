package com.example.movemates.form;

import java.util.List;

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
	
	// Entityのリストにすると必要ないフィールドが混ざるため修正
	// メニューに属するエクササイズがなくても問題ない（初期状態）ので@NotNullも不要
//	@NotNull
//	private List<MyMenuExercise> myMenuExercises;
	
	private List<Integer> myMenuExerciseIds;
	
	// 順序はListから取得できるのでフィールドに定義する必要はない
//	@NotNull
//	private List<Integer> exerciseOrder;
}
