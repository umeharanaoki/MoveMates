package com.example.movemates.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>, JpaSpecificationExecutor<Exercise> {

	public Page<Exercise> findByPurposes(Purpose purpose, Pageable pageable);
	public Page<Exercise> findByBodyParts(BodyPart bodyPart, Pageable pageable);
	
	// idのリストからexerciseを複数検索
	// vue.jsにデータを渡す用
	List<Exercise> findByIdIn(List<Integer> ids);

}
