package com.example.movemates.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

// mymenu編集ページにexerciseのデータを渡す用のDTO
@Table(name = "exercises")
@Data
public class ExerciseDTO {
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "explanation")
	private String explanation;
	
	@Column(name = "set_number")
	private String setNumber;
	
	@Column(name = "time_required")
	private Integer timeRequired;
	
	@Column(name = "type")
	private String type;
	
	// purposeオブジェクトのリストをvue.jsに渡すと謎のループが生じたので必要な名前だけリスト化する
	private List<String> purposeNames = new ArrayList<>();
	
	private List<String> bodyPartNames = new ArrayList<>();
}
