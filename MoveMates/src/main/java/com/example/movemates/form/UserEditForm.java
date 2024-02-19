package com.example.movemates.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserEditForm {
	@NotBlank(message = "IDを入力してください。")
	private String id;
	
	@NotBlank(message = "ユーザー名を入力してください。")
	private String name;

	private MultipartFile imageFile;
	
	@NotBlank(message = "メールアドレスを入力してください。")
	private String email;
	    
	private Date birthday;
	
	private String gender;

	@NotBlank(message = "パスワードを入力してください。")
	private String password;  
}
