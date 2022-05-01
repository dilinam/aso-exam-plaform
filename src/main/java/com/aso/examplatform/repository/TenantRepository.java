package com.aso.examplatform.repository;

import com.aso.examplatform.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findAllByDeleted(boolean deleted);
}
