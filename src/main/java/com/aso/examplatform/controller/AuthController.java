package com.aso.examplatform.controller;

import com.aso.examplatform.dto.JwtToken;
import com.aso.examplatform.dto.LoginRequest;
import com.aso.examplatform.dto.TenantRequest;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.User;
import com.aso.examplatform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins="*", allowedHeaders = "*")
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

    @GetMapping("/getTenants")
    public ResponseEntity<List<Tenant>> getTenants(HttpServletRequest request){
        try{
            String jwtTokenHeader = request.getHeader("Authorization");
            if(jwtTokenHeader == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(authService.getTenants(jwtTokenHeader.substring(7)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getLoggedUser")
    public ResponseEntity<User> getLoggedUser(HttpServletRequest request){
        try{
            String jwtTokenHeader = request.getHeader("Authorization");
            if(jwtTokenHeader == null){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(authService.getUser(jwtTokenHeader.substring(7)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
