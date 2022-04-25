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
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

    @Column(nullable = false, length = 100, name = "answer")
    @NotBlank(message = "Answer must be required.")
    private String answer;

    @Column(nullable = false, length = 100, name = "correctness")
    @NotBlank(message = "Correctness must be required.")
    private String correctness;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;
}
