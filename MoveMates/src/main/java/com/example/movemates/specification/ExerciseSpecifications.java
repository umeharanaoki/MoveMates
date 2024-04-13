package com.example.movemates.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;

import jakarta.persistence.criteria.Join;

public class ExerciseSpecifications {
	// 名前で部分検索
	public static Specification<Exercise> nameContains(String name) {
	    return StringUtils.hasLength(name) ? (root, query, cb) -> {
	        return cb.like(root.get("name"), "%" + name + "%");
	    } : null;
	}
	
	// 目的名で検索
	public static Specification<Exercise> purposeNameEqual(String purposeName) {
		return StringUtils.hasLength(purposeName) ? (root, query, cb) -> {
			Join<Exercise, BodyPart> purposeJoin = root.join("purposes");
			return cb.equal(purposeJoin.get("name"), purposeName);
		} : null;
	}
	
	// 体の部位名で検索
	public static Specification<Exercise> bodyPartNameEqual(String bodyPartName) {
        return StringUtils.hasLength(bodyPartName) ? (root, query, cb) -> {
            Join<Exercise, BodyPart> bodyPartJoin = root.join("bodyParts");
            return cb.equal(bodyPartJoin.get("name"), bodyPartName);
        } : null;
    }
}
