package com.aso.examplatform.repository;

import com.aso.examplatform.model.ModuleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ModuleActionRepository extends JpaRepository<ModuleAction, Long> {
    @Query("SELECT ma FROM ModuleAction ma JOIN ma.roles r WHERE r.roleId=:roleId AND :endpoint LIKE CONCAT(ma.endPoint, '%') AND ma.httpMethod=:method")
    Optional<ModuleAction> findByRoleIdAndEndPoint(@Param("roleId") Long roleId, @Param("endpoint") String endpoint, @Param("method") String method);
}
