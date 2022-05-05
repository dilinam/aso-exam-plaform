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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    @Column(nullable = false, length = 100, name = "course_name")
    @NotBlank(message = "Course name must be required.")
    private String courseName;

    private String courseDescription;
    private String courseImage;

    @Column(columnDefinition="tinyint(1)")
    private boolean status = true;

    @Column(columnDefinition="tinyint(1)")
    private boolean deleted = false;

    private String createdBy;
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "tenantId")
    private Tenant tenant;
}
