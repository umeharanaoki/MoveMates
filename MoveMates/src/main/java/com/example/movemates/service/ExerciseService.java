package com.example.movemates.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;
import com.example.movemates.form.ExerciseRegisterForm;
import com.example.movemates.repository.ExerciseRepository;

@Service
public class ExerciseService {
	private final ExerciseRepository exerciseRepository;
	
	public ExerciseService(ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}
	
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
}
