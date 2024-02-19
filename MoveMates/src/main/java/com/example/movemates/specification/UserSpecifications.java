package com.example.movemates.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.example.movemates.entity.User;

public class UserSpecifications {
	// IDで部分検索
	public static Specification<User> idContains(String id) {
		return StringUtils.hasLength(id) ? (root, query, cb) -> {
			return cb.like(root.get("id"), "%" + id + "%");
		} : null;
	}
	
	// 名前で部分検索
	public static Specification<User> nameContains(String name) {
		return StringUtils.hasLength(name) ? (root, query, cb) -> {
			return cb.like(root.get("name"), "%" + name + "%");
		} : null;
	}
	
	// メールアドレスで部分検索
	public static Specification<User> emailContains(String email) {
		return StringUtils.hasLength(email) ? (root, query, cb) -> {
			return cb.like(root.get("email"), "%" + email + "%");
		} : null;
	}
}
