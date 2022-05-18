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

    @Query("SELECT tenantUserCourse.tenantUser.user FROM TenantUserCourse tenantUserCourse WHERE tenantUserCourse.course.courseId=?1 AND tenantUserCourse.tenantUser.role.roleId=3")
    List<User> findAllCandidatesByCourseId(Long courseId);

    @Query("SELECT tenantUserCourse.tenantUser.user FROM TenantUserCourse tenantUserCourse WHERE tenantUserCourse.course.courseId=?1 AND tenantUserCourse.tenantUser.role.roleId=2")
    List<User> findAllExaminersByCourseId(Long courseId);

    @Query("SELECT uc FROM TenantUserCourse uc WHERE uc.course.courseId=?1")
    List<TenantUserCourse> findTenantUserCourseByCourseId(Long courseId);

    @Query("SELECT uc FROM TenantUserCourse uc WHERE uc.tenantUser.user.userId=?1")
    TenantUserCourse findTenantUserCourseByUserId(Long userId);
}
