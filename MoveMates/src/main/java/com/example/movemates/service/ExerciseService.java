package com.example.movemates.service;

import static com.example.movemates.specification.ExerciseSpecification.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;
import com.example.movemates.form.ExerciseEditForm;
import com.example.movemates.form.ExerciseRegisterForm;
import com.example.movemates.repository.ExerciseRepository;

@Service
public class ExerciseService {
	private final ExerciseRepository exerciseRepository;
	
	public ExerciseService(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}
	
	// 登録処理
	@Transactional
	public void create(ExerciseRegisterForm exerciseRegisterForm) {
		Exercise exercise = new Exercise();
		MultipartFile imageFile = exerciseRegisterForm.getImageFile();
		List<Purpose> purposes = exerciseRegisterForm.getPurposes();
		List<BodyPart> bodyParts = exerciseRegisterForm.getBodyParts();
		
		if(!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/exercise/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			exercise.setImageName(hashedImageName);
		}
		
		exercise.setName(exerciseRegisterForm.getName());
		exercise.setType(exerciseRegisterForm.getType());
		exercise.setPurposes(purposes);
		exercise.setBodyParts(bodyParts);
		exercise.setExplanation(exerciseRegisterForm.getExplanation());
		exercise.setSetNumber(exerciseRegisterForm.getSetNumber());
		exercise.setTimeRequired(exerciseRegisterForm.getTimeRequired());
		
		exerciseRepository.save(exercise);
	}
	
	// 更新処理
	public void update(ExerciseEditForm exerciseEditForm) {
		Exercise exercise = exerciseRepository.getReferenceById(exerciseEditForm.getId());
		MultipartFile imageFile = exerciseEditForm.getImageFile();
		
		if(!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/exercise/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			exercise.setImageName(hashedImageName);
		}
		
		exercise.setName(exerciseEditForm.getName());
		exercise.setType(exerciseEditForm.getType());
		exercise.setPurposes(exerciseEditForm.getPurposes());
		exercise.setBodyParts(exerciseEditForm.getBodyParts());
		exercise.setExplanation(exerciseEditForm.getExplanation());
		exercise.setSetNumber(exerciseEditForm.getSetNumber());
		exercise.setTimeRequired(exerciseEditForm.getTimeRequired());
		
		exerciseRepository.save(exercise);
	}
	
	// UUIDを使って生成したファイル名を返す
	public String generateNewFileName(String fileName) {
		String[] fileNames = fileName.split("\\.");
		for(int i = 0; i < fileNames.length - 1; i++) {
			fileNames[i] = UUID.randomUUID().toString();
		}
		String hashedFileName = String.join(".", fileNames);
		return hashedFileName;
	}
	
	// 画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream() ,filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 管理者用エクササイズ一覧の検索機能
	public Page<Exercise> findAdminExercises(String keyword, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Specification<Exercise> spec = Specification
			.where(nameContains(keyword));
//			.or(bodyPartEqual(keyword));
		Page<Exercise> adminRestaurantPage = exerciseRepository.findAll(spec, pageable);
		return adminRestaurantPage;
	}
}
