package com.example.movemates.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToMany
	@JoinTable(name = "exercise_purposes", 
	joinColumns = @JoinColumn(name="exercise_id", referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name="purpose_id", referencedColumnName="id"))
	private List<Purpose> purposes = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "exercise_bodyparts",
	joinColumns = @JoinColumn(name="exercise_id", referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name="bodypart_id", referencedColumnName="id"))
	private List<BodyPart> bodyParts = new ArrayList<>();
	
	@OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
	private List<MyMenuExercise> myMenuExercises = new ArrayList<>();
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
}
