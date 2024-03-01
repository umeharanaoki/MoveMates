package com.example.movemates.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mymenu_exercises")
@Data
public class MyMenuExercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "mymenu_id")
	private MyMenu myMenu;
	
	@ManyToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;
	
	@Column(name = "exercise_order")
	private Integer exerciseOrder;
}
