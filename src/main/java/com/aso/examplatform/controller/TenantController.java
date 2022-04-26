package com.aso.examplatform.controller;

import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Tenant")
@RequiredArgsConstructor
public class TenantController {
    private TenantService tenantService;

    @GetMapping("")
    public List<Tenant> getAll(){
        return tenantService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Tenant> save(@Valid @RequestBody Tenant newTenant){
        return new ResponseEntity<>(tenantService.create(newTenant), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Tenant> update(@Valid @RequestBody Tenant tenant) throws Exception{
        return new ResponseEntity<>(tenantService.update(tenant), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> get(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(tenantService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tenant> delete(@PathVariable("id") Long id) throws Exception {
        tenantService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

