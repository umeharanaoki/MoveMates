package com.example.movemates.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(message = "IDを入力してください。")
	private String id;
	
	@NotBlank(message = "ユーザー名を入力してください。")
	private String name;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "メールアドレスは正しい形式で入力してください。")
	private String email;
	
	@NotBlank(message = "性別を選択してください。")
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	@NotBlank(message = "パスワードを入力してください。")
	@Length(min = 8, message = "パスワードは8文字以上で入力してください。")
	private String password;
	
	@NotBlank(message = "パスワード（確認用）を入力してください。")
	private String passwordConfirmation;
}
