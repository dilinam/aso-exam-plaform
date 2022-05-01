package com.aso.examplatform.repository;

import com.aso.examplatform.model.TenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TenantUserRepository extends JpaRepository<TenantUser, Long> {

    @Query("SELECT ta FROM TenantUser ta WHERE ta.tenant.tenantId=?1 AND ta.user.userId=?2")
    Optional<TenantUser> findByTenantIdAndUserId(Long tenantId, Long userId);
}
