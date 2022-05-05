package com.aso.examplatform.controller;

import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @GetMapping("")
    public List<Tenant> getAll(){
        return tenantService.listAll();
    }
    @PostMapping(path = "")
    public ResponseEntity<Tenant> save(@Valid @RequestBody Tenant Tenant){
        return new ResponseEntity<>(tenantService.create(Tenant), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Tenant> update(@Valid @RequestBody Tenant tenant){
        try {
            return new ResponseEntity<>(tenantService.update(tenant), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(tenantService.get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tenant> delete(@PathVariable("id") Long id) {
        try {
            tenantService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

