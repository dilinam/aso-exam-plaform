package com.aso.examplatform.repository;

import com.aso.examplatform.model.ModuleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ModuleActionRepository extends JpaRepository<ModuleAction, Long> {
    @Query("SELECT ma FROM ModuleAction ma JOIN ma.roles r WHERE r.roleId=?1 AND ma.endPoint=?2 AND ma.httpMethod=?3")
    Optional<ModuleAction> findByRoleIdAndEndPoint(Long roleId, String endpoint, String method);
}
