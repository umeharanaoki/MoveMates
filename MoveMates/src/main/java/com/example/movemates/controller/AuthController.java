package com.example.movemates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.movemates.event.SignupEventPublisher;
import com.example.movemates.form.SignupForm;
import com.example.movemates.service.UserService;
import com.example.movemates.service.VerificationTokenService;

@Controller
public class AuthController {
	private final UserService userService;
	private final SignupEventPublisher signupEventPublisher;
	private final VerificationTokenService verificationTokenService;
	
	public AuthController(UserService userService, SignupEventPublisher signupEventPublisher, VerificationTokenService verificationTokenService) {
		this.userService = userService;
		this.signupEventPublisher = signupEventPublisher;
		this.verificationTokenService = verificationTokenService;
	}
	// ログイン画面を返す
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	// 会員登録フォームを返す
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "auth/signup";
	}
	
//	// 会員登録フォーム入力後の処理
//	@PostMapping("/signup")
//    public String signup(@ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {      
//        // メールアドレスが登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
//        if (userService.isEmailRegistered(signupForm.getEmail())) {
//            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
//            bindingResult.addError(fieldError);                       
//        }    
//        
//        // パスワードとパスワード（確認用）の入力値が一致しなければ、BindingResultオブジェクトにエラー内容を追加する
//        if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
//            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
//            bindingResult.addError(fieldError);
//        }        
//        
//        if (bindingResult.hasErrors()) {
//            return "auth/signup";
//        }
//        
//        User createdUser = userService.create(signupForm);
//        String requestUrl = new String(httpServletRequest.getRequestURL());
//        signupEventPublisher.publishSignupEvent(createdUser, requestUrl);
//        redirectAttributes.addFlashAttribute("successMessage", "ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");
//        
//        return "redirect:/";
//    }
//	
//	@GetMapping("/signup/verify")
//    public String verify(@RequestParam(name = "token") String token, Model model) {
//        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
//        
//        if (verificationToken != null) {
//            User user = verificationToken.getUser();  
//            userService.enableUser(user);
//            String successMessage = "会員登録が完了しました。";
//            model.addAttribute("successMessage", successMessage);            
//        } else {
//            String errorMessage = "トークンが無効です。";
//            model.addAttribute("errorMessage", errorMessage);
//        }
//        
//        return "auth/verify";         
//    }    
//	
}
