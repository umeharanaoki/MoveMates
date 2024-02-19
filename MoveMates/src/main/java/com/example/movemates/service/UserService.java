package com.example.movemates.service;

import static com.example.movemates.specification.UserSpecifications.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.movemates.entity.Role;
import com.example.movemates.entity.User;
import com.example.movemates.form.SignupForm;
import com.example.movemates.form.UserEditForm;
import com.example.movemates.repository.RoleRepository;
import com.example.movemates.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;        
        this.passwordEncoder = passwordEncoder;
    }    
    
    // 登録機能
    @Transactional
    public User create(SignupForm signupForm) {
        User user = new User();
        MultipartFile imageFile = signupForm.getImageFile();
        Role role = roleRepository.findByName("ROLE_GENERAL");
        // 入力が任意なのでnullの場合の処理を先に済ませる
        Date birthday = (signupForm.getBirthday() == null) ? null : signupForm.getBirthday();
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            user.setImageName(hashedImageName);
        }
        
        user.setId(signupForm.getId());
        user.setName(signupForm.getName());
        user.setEmail(signupForm.getEmail());
        user.setGender(signupForm.getGender());
        user.setBirthday(birthday);
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setRole(role);
        user.setEnabled(false);        
        
        return userRepository.save(user);
    }    
    
    // 更新機能
    @Transactional
    public void update(UserEditForm userEditForm) {
  	// 編集用なので元の情報をIdから検索して格納する
    	User user = userRepository.getReferenceById(userEditForm.getId());
    	MultipartFile imageFile = userEditForm.getImageFile();
    	Date birthday = (userEditForm.getBirthday() == null) ? null : userEditForm.getBirthday();
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            user.setImageName(hashedImageName);
        }
        
        user.setId(userEditForm.getId());
        user.setName(userEditForm.getName());
        user.setEmail(userEditForm.getEmail());
        user.setGender(userEditForm.getGender());
        user.setBirthday(birthday);  
        
        userRepository.save(user);
    }
  
    // 管理者用一覧の検索機能
    // or検索なので引数は全てkeyword
    public Page<User> findAdminUsers(String keyword, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Specification<User> spec = Specification
				 .where(idContains(keyword))
				 .or(nameContains(keyword))
				 .or(emailContains(keyword));
	    Page<User> adminUserPage = userRepository.findAll(spec, pageable);
	    return adminUserPage;
	}
    
    // UUIDを使って生成したファイル名を返す
    public String generateNewFileName(String fileName) {
        String[] fileNames = fileName.split("\\.");                
        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();            
        }
        String hashedFileName = String.join(".", fileNames);
        return hashedFileName;
    }     
    
    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {           
        try {
            Files.copy(imageFile.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }          
    }
    
    // メールアドレスが登録済みかどうかをチェックする
    public boolean isEmailRegistered(String email) {
        User user = userRepository.findByEmail(email);  
        return user != null;
    }  
    
    // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }
    
    // ユーザーを有効にする
    @Transactional
    public void enableUser(User user) {
        user.setEnabled(true); 
        userRepository.save(user);
    }
    
    // メールアドレスが変更されたかどうか確認
    public boolean isEmailChanged(UserEditForm userEditForm) {
    	User currentUser = userRepository.getReferenceById(userEditForm.getId());
    	return !userEditForm.getEmail().equals(currentUser.getEmail());
    }
}
