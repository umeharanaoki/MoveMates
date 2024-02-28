package com.example.movemates.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Favorite;
import com.example.movemates.entity.User;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.FavoriteRepository;
import com.example.movemates.security.UserDetailsImpl;
import com.example.movemates.service.FavoriteService;

// ページ遷移したくないときは@RestControllerを指定
@RestController
public class AjaxFavoriteController {
	private final FavoriteService favoriteService;
	private final FavoriteRepository favoriteRepository;
	private final ExerciseRepository exerciseRepository;
	
	AjaxFavoriteController(FavoriteService favoriteService, FavoriteRepository favoriteRepository, ExerciseRepository exerciseRepository) {
		this.favoriteService = favoriteService;
		this.favoriteRepository = favoriteRepository;
		this.exerciseRepository = exerciseRepository;
	}
	
	// Ajaxからのリクエストを受け取る
	@PostMapping("/exercises/{exercise_id}/favorites/toggle")
	public Integer toggleFavorite(@PathVariable(name = "exercise_id") Integer exerciseId, 
								  @AuthenticationPrincipal UserDetailsImpl userDetailsImpl)
	{
		User user = userDetailsImpl.getUser();
		Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
		// Serviceのメソッドを使用してデータベースの操作
		favoriteService.toggleFavorite(user, exercise);
		
		Favorite favorite = favoriteRepository.findByUserAndExercise(user, exercise);
		// ビューではなくデータを返す
		return favorite.getStatus();
	}
}


