package com.aso.examplatform.controller;

import com.aso.examplatform.model.Role;
import com.aso.examplatform.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Role")
@RequiredArgsConstructor
public class RoleController {
    private RoleService roleService;

    @GetMapping("")
    public List<Role> getAll(){
        return roleService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Role> save(@Valid @RequestBody Role newRole){
        return new ResponseEntity<>(roleService.create(newRole), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Role> update(@Valid @RequestBody Role role){
        try {
            return new ResponseEntity<>(roleService.update(role), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(roleService.get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
