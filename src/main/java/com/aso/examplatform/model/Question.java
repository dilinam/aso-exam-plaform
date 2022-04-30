package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @Column(nullable = false, length = 100, name = "question")
    @NotBlank(message = "Question must be required.")
    private String question;

    @Column(nullable = false, name = "order")
    @NotBlank(message = "Order must be required.")
    private int order;

    @Column(columnDefinition="tinyint(1)")
    private boolean status = true;

    private String filePath;

    @Column(columnDefinition="tinyint(1)")
    private boolean deleted = false;

    private String createdBy;
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "questionTypeId")
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "examId")
    private Exam exam;

    @OneToMany
    private List<Answer> answers;
}
