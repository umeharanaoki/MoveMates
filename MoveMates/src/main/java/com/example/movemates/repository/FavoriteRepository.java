package com.example.movemates.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Favorite;
import com.example.movemates.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	// ユーザーがその店をお気に入り登録しているかを判断するための情報
	public Favorite findByUserAndExercise(User user, Exercise exercise);
	// お気に入り登録者数を計算するためのメソッド
	public Integer countFavoriteUsersByExerciseId(Integer exerciseId);
	// お気に入り一覧ページ
	public Page<Favorite> findByUserAndStatus(User user, Integer status, Pageable pageable);	
}
