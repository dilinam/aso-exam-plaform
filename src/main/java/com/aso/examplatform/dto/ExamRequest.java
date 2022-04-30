package com.aso.examplatform.dto;

import com.aso.examplatform.model.Exam;
import com.aso.examplatform.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequest {
    @NotNull
    private List<Question> questions;
    @Valid
    private Exam exam;
}
