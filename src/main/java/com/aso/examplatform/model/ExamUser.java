package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examUserId;

    @ManyToOne
    @JoinColumn(name = "examId")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private Double marks =null;

    @Column(columnDefinition="tinyint(1)")
    private boolean isCompleted = false;

    @Column
    private Long startTime = null;

    @Column
    private Long endTime = null;

    public ExamUser(Exam exam, User user) {
        this.exam = exam;
        this.user = user;
    }
}
