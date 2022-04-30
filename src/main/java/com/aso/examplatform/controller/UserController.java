package com.aso.examplatform.controller;

import com.aso.examplatform.dto.UserRequest;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.User;
import com.aso.examplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserRequest userRequest,HttpServletRequest request){
        return ResponseEntity.ok(userService.addUser(userRequest,(Tenant) request.getAttribute("TENANT")));
    }
}
