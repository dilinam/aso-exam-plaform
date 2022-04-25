package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionTypeId;

    @Column(nullable = false, length = 100, name = "question_type_name")
    @NotBlank(message = "Question type name must be required.")
    private String questionTypeName;
}
