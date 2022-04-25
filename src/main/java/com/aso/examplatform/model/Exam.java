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
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examId;

    @Column(nullable = false, length = 100, name = "exam_name")
    @NotBlank(message = "Exam name must be required.")
    private String examName;
    private String examDescription;

    @Column(nullable = false, length = 50, name = "date_time")
    @NotBlank(message = "Date and time must be required.")
    private String dateTime;

    @Column(nullable = false, length = 50, name = "duration")
    @NotBlank(message = "Duration must be required.")
    private String duration;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean deleted;

    private String createdBy;
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
}
