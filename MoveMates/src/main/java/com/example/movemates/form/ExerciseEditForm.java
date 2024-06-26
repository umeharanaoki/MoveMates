package com.example.movemates.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseEditForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "アクティビティ名を入力してください。")
	private String name;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "選択してください。")
	private String type;
	
	@NotNull(message = "最低1つ選択してください。")
//	private List<Purpose> purposes;
	private List<String> purposeNames;  // オブジェクトリストを渡すとthymeleafが上手く処理してくれなかったのでNameリストを渡す
	
	@NotNull(message = "最低1つ選択してください。")
//	private List<BodyPart> bodyParts;
	private List<String> bodyPartNames;  // 同上
	
	@NotBlank(message = "アクティビティの手順を入力してください。")
	private String explanation;
	
	@NotBlank(message = "目安のセット回数を入力してください。")
	private String setNumber;
	
	@NotNull(message = "目安の所要時間を入力してください。（秒単位）")
	private Integer timeRequired;
}
