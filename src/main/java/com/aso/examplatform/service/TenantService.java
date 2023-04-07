package com.aso.examplatform.service;

import com.aso.examplatform.repository.TenantRepository;
import com.aso.examplatform.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    public List<Tenant> listAll(){
        return tenantRepository.findAllByDeleted(false);
    }
    public Tenant create(Tenant tenant){
        tenantRepository.save(tenant);
        return tenant;
    }
    public Tenant update(Tenant tenant) throws Exception{
        if (tenantRepository.findById(tenant.getTenantId()).isEmpty()){
            throw new Exception("Tenant not found");
        }
        tenantRepository.save(tenant);
        return tenant;
    }
    public Tenant get(Long id) throws Exception{
        return tenantRepository.findById(id).orElseThrow(() -> new Exception("Tenant not found"));
    }
    public void delete(Long id) throws Exception{
        Optional<Tenant> tenantOptional = tenantRepository.findById(id);
        if (tenantOptional.isEmpty()){
            throw new Exception("Tenant not found");
        }
        Tenant tenant = tenantOptional.get();
        tenant.setDeleted(true);
        tenantRepository.save(tenant);
    }
}
