package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examId;
    private String examName;
    private String examDescription;
    private String dateTime;
    private String duration;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean deleted;

    private String createdBy;
    private String createdAt;

    @ManyToOne
    @Column(name = "courseId")
    private Course course;
}
