package com.aso.examplatform.repository;

import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.TenantUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TenantUserRepository extends JpaRepository<TenantUser, Long> {

    @Query("SELECT ta FROM TenantUser ta WHERE ta.tenant.tenantId=?1 AND ta.user.userId=?2")
    Collection<? extends TenantUserCourse> findByTenantIdAndUserId(Long tenantId, Long userId);
}
