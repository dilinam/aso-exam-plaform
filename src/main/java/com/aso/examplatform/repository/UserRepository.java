package com.aso.examplatform.repository;

import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByDeleted(boolean deleted);

    @Query("SELECT tu.tenant FROM TenantUser tu JOIN User u ON tu.user=u WHERE u.username=?1")
    List<Tenant> findTenants(String username);
}
