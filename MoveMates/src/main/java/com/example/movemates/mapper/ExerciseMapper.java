package com.example.movemates.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.movemates.dto.ExerciseDTO;
import com.example.movemates.entity.BodyPart;
import com.example.movemates.entity.Exercise;
import com.example.movemates.entity.Purpose;

// exerciseエンティティをDTOに変換
// axiosでデータを送るときに外部キーをオブジェクトのまま持つことで不都合が生じたので対応
public class ExerciseMapper {
	public static ExerciseDTO mapToDTO(Exercise exercise) {
		ExerciseDTO exerciseDTO = new ExerciseDTO();
		
		exerciseDTO.setId(exercise.getId());
		exerciseDTO.setName(exercise.getName());
		exerciseDTO.setImageName(exercise.getImageName());
		exerciseDTO.setExplanation(exercise.getExplanation());
		exerciseDTO.setSetNumber(exercise.getSetNumber());
		exerciseDTO.setSetNumber(exercise.getSetNumber());
		exerciseDTO.setTimeRequired(exercise.getTimeRequired());
		exerciseDTO.setType(exercise.getType());
		exerciseDTO.setPurposeNames(mapPurposesToStrings(exercise.getPurposes()));
		exerciseDTO.setBodyPartNames(mapBodyPartsToStrings(exercise.getBodyParts()));
		
		return exerciseDTO;
	}
	
	// PurposeオブジェクトのリストをPurposeのnameのリストに変換する
	public static List<String> mapPurposesToStrings(List<Purpose> purposes) {
        List<String> purposeNames = new ArrayList<>();
        for (Purpose purpose : purposes) {
            purposeNames.add(purpose.getName());
        }
        return purposeNames;
    }
	
	// PurposeオブジェクトのリストをPurposeのnameのリストに変換する
	public static List<String> mapBodyPartsToStrings(List<BodyPart> bodyParts) {
	    List<String> bodyPartNames = new ArrayList<>();
	    for (BodyPart bodyPart : bodyParts) {
	        bodyPartNames.add(bodyPart.getName());
	    }
	    return bodyPartNames;
	}
}
