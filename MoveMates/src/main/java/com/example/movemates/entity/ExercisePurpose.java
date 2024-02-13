package com.example.movemates.entity;

import com.example.movemates.embeddable.ExercisePurposeId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "exercises_purposes")
@Data
public class ExercisePurpose {
	// 複合主キー
	@EmbeddedId
	private ExercisePurposeId id;
}
