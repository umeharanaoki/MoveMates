package com.example.movemates.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;

public class ExerciseSpecifications {
	// 名前で部分検索
	public static Specification<Exercise> nameContains(String name) {
	    return StringUtils.hasLength(name) ? (root, query, cb) -> {
	        return cb.like(root.get("name"), "%" + name + "%");
	    } : null;
	}
	
	// 体の部位で検索
	public static Specification<Exercise> bodyPartEqual(BodyPart bodyPart) {
		return ObjectUtils.isEmpty(bodyPart) ? null : (root, query, cb) -> {
			return cb.equal(root.get("bodyPart"), bodyPart.getId());
		};
	}
}
