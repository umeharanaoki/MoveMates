package com.example.movemates.embeddable;

import java.io.Serializable;

// import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ExercisePurposeId implements Serializable {
	
	// 外部キー指定
	private Integer exerciseId;
	private Integer purposeId;
	
	// コンストラクタ
    public ExercisePurposeId(Integer exerciseId, Integer purposeId) {
        this.exerciseId = exerciseId;
        this.purposeId = purposeId;
    }
}
