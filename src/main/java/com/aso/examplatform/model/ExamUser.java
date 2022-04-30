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
    private double marks;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isCompleted;

    @Column
    private long startTime;

    @Column
    private long endTime;

}
