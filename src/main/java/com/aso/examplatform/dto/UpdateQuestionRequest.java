package com.aso.examplatform.dto;

import com.aso.examplatform.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionRequest {
    @NotNull
    private List<Question> questions;
    @NotNull
    private Long examId;
}
