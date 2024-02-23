package com.example.movemates.service;

import org.springframework.stereotype.Service;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Favorite;
import com.example.movemates.entity.User;
import com.example.movemates.repository.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
		
	public FavoriteService(FavoriteRepository favoriteRepository) {
		this.favoriteRepository = favoriteRepository;
	}
	
	@Transactional
	public void toggleFavorite(User user, Exercise exercise) {
		Favorite favorite = favoriteRepository.findByUserAndExercise(user, exercise);
		
		if(favorite != null) {
			// すでに値がある場合、statusを切り替える
			favorite.setStatus(1 - favorite.getStatus());
		} else {
			// まだ値がない（そのユーザーがその店舗のお気に入りボタンを初めて押す）とき
			favorite = new Favorite();
			favorite.setUser(user);
			favorite.setExercise(exercise);
			favorite.setStatus(1);
		}
		favoriteRepository.save(favorite);
	}
}