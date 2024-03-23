package com.example.movemates.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movemates.entity.EncouragingMessage;
import com.example.movemates.entity.ExerciseLog;
import com.example.movemates.entity.User;
import com.example.movemates.form.UserEditForm;
import com.example.movemates.repository.EncouragingMessageRepository;
import com.example.movemates.repository.ExerciseLogRepository;
import com.example.movemates.repository.UserRepository;
import com.example.movemates.security.UserDetailsImpl;
import com.example.movemates.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserRepository userRepository;
	private final UserService userService;
	private final ExerciseLogRepository exerciseLogRepository;
	private final EncouragingMessageRepository encouragingMessageRepository;
	
	public UserController(UserRepository userRepository, UserService userService, ExerciseLogRepository exerciseLogRepository, EncouragingMessageRepository encouragingMessageRepository) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.exerciseLogRepository = exerciseLogRepository;
		this.encouragingMessageRepository = encouragingMessageRepository;
	}
	
	// マイページ
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		List<ExerciseLog> exerciseLogs = exerciseLogRepository.findByUser(user);
		// 運動の記録から日付を取り出してリスト化→とりあえずカレンダーにマークを表示するために簡素化
		// カレンダーのマークをクリックしたときに運動の記録の詳細を表示させるならExerciseLogのリストで渡す必要あり
		List<LocalDate> exerciseDays = exerciseLogs.stream()
		                                          .map(ExerciseLog::getExerciseDay)
		                                          .map(Timestamp::toLocalDateTime)
		                                          .map(LocalDateTime::toLocalDate)
		                                          .collect(Collectors.toList());
		
		int numberOfExerciseWeek = 0;
		
		if (!exerciseDays.isEmpty()) {
			// 直近の運動日にあたるインデックスを指定して取得
		    LocalDate latestExerciseDate = exerciseDays.get(exerciseDays.size() - 1);
		    // 今日の日付を取得
		    LocalDate today = LocalDate.now();
		    // 基準日を今日に設定
		    LocalDate referenceDate = today;
		    // 直近の運動日から今日までの日数を計算
		    long daysDifference = ChronoUnit.DAYS.between(latestExerciseDate, referenceDate);
		    // 継続の開始日を一旦直近の運動日に指定
		    LocalDate continuationStartDate = latestExerciseDate;
		    // 1週間以内に運動の記録がある場合、継続が切れるまでさかのぼる
		    if (daysDifference <= 7) {
		    	for (int i = 1; i <= exerciseDays.size() - 1; i++) {
		    		referenceDate = latestExerciseDate;
		    		latestExerciseDate = exerciseDays.get(exerciseDays.size() - (1 + i));
		    		daysDifference = ChronoUnit.DAYS.between(latestExerciseDate, referenceDate);
		    		
		    		// 前の運動日から1週空いていたらループ終了
		    		if (daysDifference > 7) {
		    			break;
		    		}
		    		// 継続した運動の開始日を更新
		    		continuationStartDate = latestExerciseDate;
		    	}
		    	// 継続している運動の開始日と今日の間の週数を取得
		    	numberOfExerciseWeek = Math.toIntExact(ChronoUnit.WEEKS.between(continuationStartDate, today)) + 1;  // ChronoUnit.WEEKS.betweenが1週間未満のとき0を返すので+1する
		    }
		}
		
		EncouragingMessage encouragingMessage = encouragingMessageRepository.findFirstByConsecutiveWeeksLessThanEqualOrderByConsecutiveWeeksDesc(numberOfExerciseWeek);
		
		model.addAttribute("user", user);
		model.addAttribute("exerciseDays", exerciseDays);
		model.addAttribute("encouragingMessage", encouragingMessage);
		
		return "user/index";
	}
	
	// ユーザー情報確認
	@GetMapping("/show")
	public String show(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		
		model.addAttribute("user", user);
		
		return "user/show";
	}
	
	// ユーザー情報編集ページ
	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), null, user.getEmail());
		model.addAttribute("userEditForm", userEditForm);
		return "user/edit";
	}
	
	// ユーザー情報更新
	@PostMapping("/update")
    public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
        if(userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);                       
        }
        
        if(bindingResult.hasErrors()) {
            return "user/edit";
        }
        
        userService.update(userEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
        
        return "redirect:/user/show";
    }    
}
