package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.MyMenu;

public interface MyMenuRepository extends JpaRepository<MyMenu, Integer> {

}
