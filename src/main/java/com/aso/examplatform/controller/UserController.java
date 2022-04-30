package com.aso.examplatform.controller;

import com.aso.examplatform.dto.UserRequest;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.User;
import com.aso.examplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getuser(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @PostMapping("")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request){
        TenantUser tenantUser = userService.addUser(userRequest,(Tenant) request.getAttribute("TENANT"));
        if(tenantUser == null){
            Map<String, String> validationResults = new HashMap<>();
            validationResults.put("roleId", "Invalid Role");
            return ResponseEntity.badRequest().body(validationResults);
        }
        return ResponseEntity.ok(userService.addUser(userRequest,(Tenant) request.getAttribute("TENANT")));
    }
}
