package com.example.movemates.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}
