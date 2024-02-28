package com.example.movemates.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "mymenu_exercises")
@Data
public class MyMenuExercise implements Serializable {
	
	@EmbeddedId
	private PrimaryKey pk;
	
	@Column(name = "exercise_order")
	private Integer exerciseOrder;
	
	@ManyToOne
	@MapsId("mymenuId")
	private MyMenu myMenu;
	
	@ManyToOne
	@MapsId("exerciseId")
	private Exercise exercise;
	
	public MyMenuExercise(MyMenu myMenu, Exercise exercise, Integer exerciseOrder) {
		this.myMenu = myMenu;
		this.exercise = exercise;
		this.exerciseOrder = exerciseOrder;
	}
	
	@Getter
	@Embeddable
	public static class PrimaryKey implements Serializable {
		
		@Column(name = "mymenu_id")
		private Integer mymenuId;
		
		@Column(name = "exercise_id")
		private Integer exerciseId;
	}
}
