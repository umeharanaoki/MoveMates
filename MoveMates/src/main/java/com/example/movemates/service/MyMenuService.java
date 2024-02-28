package com.example.movemates.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.movemates.entity.MyMenu;
import com.example.movemates.entity.User;
import com.example.movemates.repository.MyMenuRepository;

@Service
public class MyMenuService {
	private final MyMenuRepository myMenuRepository;

    public MyMenuService(MyMenuRepository myMenuRepository) {
        this.myMenuRepository = myMenuRepository;
    }

    public void createDefaultMyMenus(User user) {
        // ユーザーIDをもとにマイメニューを作成する
        MyMenu myMenu1 = new MyMenu();
        myMenu1.setName("メニュー1");
        myMenu1.setUser(user); // UserエンティティのコンストラクタでIDを指定する

        MyMenu myMenu2 = new MyMenu();
        myMenu2.setName("メニュー2");
        myMenu2.setUser(user);

        MyMenu myMenu3 = new MyMenu();
        myMenu3.setName("メニュー3");
        myMenu3.setUser(user);

        // 作成したマイメニューを保存する
        myMenuRepository.saveAll(Arrays.asList(myMenu1, myMenu2, myMenu3));
    }
}
