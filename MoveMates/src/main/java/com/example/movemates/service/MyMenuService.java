package com.example.movemates.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.MyMenuExercise;
import com.example.movemates.entity.User;
import com.example.movemates.form.MyMenuEditForm;
import com.example.movemates.repository.MyMenuExerciseRepository;
import com.example.movemates.repository.MyMenuRepository;

@Service
public class MyMenuService {
	private final MyMenuRepository myMenuRepository;
//	private final ExerciseRepository exerciseRepository;
	private final MyMenuExerciseRepository myMenuExerciseRepository;

    public MyMenuService(MyMenuRepository myMenuRepository, MyMenuExerciseRepository myMenuExerciseRepository) {
        this.myMenuRepository = myMenuRepository;
//        this.exerciseRepository = exerciseRepository;
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
    
    // エクササイズをマイメニューに追加する
    @Transactional
    public void addExercisesToMyMenu(MyMenu myMenu, List<Exercise> exercises) {
        Integer exerciseOrder = myMenu.getMyMenuExercises().size() + 1;
        
        // マイメニューにエクササイズを追加する処理
        List<MyMenuExercise> myMenuExercises = new ArrayList<>();
        for (Exercise exercise : exercises) {
            MyMenuExercise myMenuExercise = new MyMenuExercise();
            myMenuExercise.setMyMenu(myMenu);
            myMenuExercise.setExercise(exercise);
            myMenuExercise.setExerciseOrder(exerciseOrder++);
            
            myMenuExercises.add(myMenuExercise);
        }
        
        // マイメニューのエクササイズ一覧に追加
        myMenuExerciseRepository.saveAll(myMenuExercises);
    }
    
    // マイメニューの内容を更新する
    @Transactional
    public void update(MyMenuEditForm myMenuEditForm) {
    	MyMenu myMenu = 
    }
}
