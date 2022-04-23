package com.aso.examplatform.controller;

import com.aso.examplatform.dto.JwtToken;
import com.aso.examplatform.dto.LoginRequest;
import com.aso.examplatform.dto.TenantRequest;
import com.aso.examplatform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginRequest request){
        JwtToken jwtToken = authService.generateJwtWithoutTenant(request);
        if(jwtToken == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(jwtToken);
    }

    @PostMapping("/setTenant")
    public ResponseEntity<JwtToken> setTenant(@RequestBody TenantRequest request){
        JwtToken jwtToken = authService.generateJwtWithTenant(request);
        if(jwtToken == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().body(jwtToken);
    }

}
