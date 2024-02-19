package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
