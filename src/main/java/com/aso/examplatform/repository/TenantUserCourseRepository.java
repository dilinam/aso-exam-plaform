package com.aso.examplatform.repository;

import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.TenantUserCourse;
import com.aso.examplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenantUserCourseRepository extends JpaRepository<TenantUserCourse, Long> {
    @Query("SELECT u FROM TenantUser u JOIN TenantUserCourse uc WHERE uc.course.courseId=?1")
    List<TenantUser> findTenantUserByCourseId(Long courseId);

    @Query("SELECT tenantUser.tenantUser.user FROM TenantUserCourse tenantUser WHERE tenantUser.course.courseId=?1")
    List<User> findAllByCourseId(Long courseId);
}
