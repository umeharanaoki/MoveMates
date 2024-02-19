package com.example.movemates.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mymenus")
@Data
public class MyMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	
	@ManyToMany
	@JoinTable(name = "mymenu_exercises",
	joinColumns = @JoinColumn(name="mymenu_id", referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name="exercise_id", referencedColumnName="id"))
	private List<Exercise> exercises = new ArrayList<>();
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
}
