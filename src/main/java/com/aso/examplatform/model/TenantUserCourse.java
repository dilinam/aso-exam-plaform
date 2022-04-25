package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantUserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tenantUserCourseId;

    @ManyToOne
    @JoinColumn(name = "tenant_user_id")
    private TenantUser tenantUser;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
}
