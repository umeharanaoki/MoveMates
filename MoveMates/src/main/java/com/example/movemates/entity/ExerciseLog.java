package com.example.movemates.entity;

import java.sql.Timestamp;

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
@Table(name = "exercise_logs")
@Data
public class ExerciseLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	
	@JoinColumn(name = "mymenu_id")
	@ManyToOne
	private MyMenu myMenu;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp exerciseDay;
	
	@Column(name = "exercise_duration")
	private Integer exerciseDuration;
}
