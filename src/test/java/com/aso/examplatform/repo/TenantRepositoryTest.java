package com.aso.examplatform.repo;

import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.repository.TenantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TenantRepositoryTest {
    @Autowired
    private TenantRepository tenantRepository;

    @Test
    public void testfindall(){
        List<Tenant> tenants = tenantRepository.findAll();
        for (Tenant tenant:tenants) {
            System.out.println(tenant);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        Assertions.assertNotNull(tenantRepository.findAll());
        Assertions.assertIterableEquals(tenants,tenantRepository.findAll());
    }
    @Test
    public void testfindallnotdeleted(){
        List<Tenant> tenants = tenantRepository.findAllByDeleted(false);
        for (Tenant tenant:tenants) {
            System.out.println(tenant);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        Assertions.assertNotNull(tenantRepository.findAll());
        Assertions.assertIterableEquals(tenants,tenantRepository.findAll());
    }


}
