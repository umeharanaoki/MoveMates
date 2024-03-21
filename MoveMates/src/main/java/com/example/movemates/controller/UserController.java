package com.example.movemates.controller;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
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

import com.example.movemates.entity.ExerciseLog;
import com.example.movemates.entity.User;
import com.example.movemates.form.UserEditForm;
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
	
	public UserController(UserRepository userRepository, UserService userService, ExerciseLogRepository exerciseLogRepository) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.exerciseLogRepository = exerciseLogRepository;
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
		
//		System.out.println(exerciseDays);

		model.addAttribute("user", user);
		model.addAttribute("exerciseDays", exerciseDays);
		
		return "user/index";
	}
	
	// カレンダーページ
	@GetMapping("/calendar")
	public String calendar(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
	    User user = userDetailsImpl.getUser();
	    List<ExerciseLog> exerciseLogs = exerciseLogRepository.findByUser(user);
	    
	    // カレンダーを生成するための年と月を取得
	    LocalDate today = LocalDate.now();
	    int currentYear = today.getYear();
	    int currentMonth = today.getMonthValue();
	    
	    // カレンダーを生成
	    List<List<Integer>> calendarData = generateCalendar(currentYear, currentMonth, exerciseLogs);
	    
	    model.addAttribute("user", user);
	    model.addAttribute("calendarData", calendarData);
	    model.addAttribute("currentYear", currentYear);
	    model.addAttribute("currentMonth", currentMonth);
	    
	    return "user/calendar";
	}
	
	// カレンダー
	private List<List<Integer>> generateCalendar(int year, int month, List<ExerciseLog> exerciseLogs) {
	    List<List<Integer>> calendarData = new ArrayList<>();
	    // 月の日数を取得
	    int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
	    // 月初めの曜日を取得
	    DayOfWeek firstDayOfWeek = YearMonth.of(year, month).atDay(1).getDayOfWeek();
	    // カレンダーの日数を計算
	    int cellsInCalendar = (firstDayOfWeek.getValue() - 1 + daysInMonth + 6) / 7 * 7;

	    int date = 1;
	    for (int i = 0; i < cellsInCalendar / 7; i++) {
	        List<Integer> row = new ArrayList<>();
	        for (int j = 0; j < 7; j++) {
	            if (i == 0 && j < firstDayOfWeek.getValue() - 1 || date > daysInMonth) {
	                row.add(null); // 空セル
	            } else {
	                LocalDate currentDate = LocalDate.of(year, month, date);
	                boolean hasExercise = exerciseLogs.stream()
	                        .anyMatch(log -> log.getExerciseDay().toLocalDateTime().toLocalDate().equals(currentDate));
	                row.add(hasExercise ? 1 : 0);
	                date++;
	            }
	        }
	        calendarData.add(row);
	    }

	    return calendarData;
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
