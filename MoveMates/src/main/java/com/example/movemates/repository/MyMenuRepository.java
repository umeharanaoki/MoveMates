package com.example.movemates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.User;

public interface MyMenuRepository extends JpaRepository<MyMenu, Integer> {
	public List<MyMenu> findByUser(User user);
	
}
