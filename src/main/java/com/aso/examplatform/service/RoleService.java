package com.aso.examplatform.service;

import com.aso.examplatform.model.Exam;
import com.aso.examplatform.model.Role;
import com.aso.examplatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listAll(){
        return (List<Role>) roleRepository.findAll();
    }
    public Role create(Role role){
        roleRepository.save(role);
        return role;
    }
    public Role update(Role role) throws Exception{
        if (!roleRepository.findById(role.getRoleId()).isPresent()){
            throw new Exception("Role not found");
        }
        roleRepository.save(role);
        return role;
    }
    public Role get(Long id) throws Exception{
        Optional<Role> result = roleRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Role not found"));
    }

}
