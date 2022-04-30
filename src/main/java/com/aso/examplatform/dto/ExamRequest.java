package com.aso.examplatform.dto;

import com.aso.examplatform.model.Exam;
import com.aso.examplatform.model.Question;

import java.util.List;

public class ExamRequest {
    public List<Question> question;
    public Exam exam;
}
