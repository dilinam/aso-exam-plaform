package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    private String courseName;
    private String courseDescription;
    private String courseImage;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean deleted;

    private String createdBy;
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "tenantId")
    private Tenant tenant;
}
