package com.example.movemates.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movemates.dto.ExerciseDTO;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.MyMenuExercise;
import com.example.movemates.entity.User;
import com.example.movemates.form.MyMenuEditForm;
import com.example.movemates.repository.ExerciseRepository;
import com.example.movemates.repository.MyMenuExerciseRepository;
import com.example.movemates.repository.MyMenuRepository;

@Service
public class MyMenuService {
	private final MyMenuRepository myMenuRepository;
	private final ExerciseRepository exerciseRepository;
	private final MyMenuExerciseRepository myMenuExerciseRepository;

    public MyMenuService(MyMenuRepository myMenuRepository, ExerciseRepository exerciseRepository, MyMenuExerciseRepository myMenuExerciseRepository) {
        this.myMenuRepository = myMenuRepository;
        this.exerciseRepository = exerciseRepository;
        this.myMenuExerciseRepository = myMenuExerciseRepository;
    }
    
    // ユーザー登録時にデフォルトのメニューを作成
    @Transactional
    public void createDefaultMyMenus(User user) {
        MyMenu myMenu1 = new MyMenu();
        myMenu1.setName("メニュー1");
        myMenu1.setUser(user); // UserエンティティのコンストラクタでIDを指定する

        MyMenu myMenu2 = new MyMenu();
        myMenu2.setName("メニュー2");
        myMenu2.setUser(user);

        MyMenu myMenu3 = new MyMenu();
        myMenu3.setName("メニュー3");
        myMenu3.setUser(user);

        myMenuRepository.saveAll(Arrays.asList(myMenu1, myMenu2, myMenu3));
    }
    
    // マイメニューの内容を更新する
    @Transactional
    public void update(MyMenuEditForm myMenuEditForm) {
    	MyMenu myMenu = myMenuRepository.getReferenceById(myMenuEditForm.getMyMenuId());
    	
    	myMenu.setName(myMenuEditForm.getMyMenuName());

    	// MyMenuに関連付けられたExerciseをすべて削除
    	myMenuExerciseRepository.deleteByMyMenuId(myMenu.getId());
    	
    	List<ExerciseDTO> exerciseDtoList = myMenuEditForm.getMyMenuExerciseDTOs();
    	
    	// myMenuExerciseDTOsリストを処理してMyMenuに関連付ける
    	for(int i = 0; i < exerciseDtoList.size(); i++) {
    		ExerciseDTO dto = exerciseDtoList.get(i);
    		Exercise exercise = exerciseRepository.getReferenceById(dto.getId());
    		
    		// MyMenuExerciseオブジェクトを作成し、関連付け、exerciseOrderを設定
    		MyMenuExercise myMenuExercise = new MyMenuExercise();
    		myMenuExercise.setMyMenu(myMenu);
    		myMenuExercise.setExercise(exercise);
    		myMenuExercise.setExerciseOrder(i + 1);  // indexは0から始まるので+1する
    		
    		// MyMenuにMyMenuExerciseを追加
    		myMenu.getMyMenuExercises().add(myMenuExercise);
    	}
    	
    	// MyMenuを保存
    	myMenuRepository.save(myMenu);
    }
}
