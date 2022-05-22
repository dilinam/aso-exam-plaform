package com.aso.examplatform.service;

import com.aso.examplatform.dto.JwtToken;
import com.aso.examplatform.dto.LoginRequest;
import com.aso.examplatform.dto.TenantRequest;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.TenantUserRepository;
import com.aso.examplatform.repository.UserRepository;
import com.aso.examplatform.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TenantUserRepository tenantUserRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtToken generateJwtWithoutTenant(LoginRequest loginRequest){

        if(loginRequest.getUsername() == null || loginRequest.getPassword() == null){
            return null;
        }

        // compare username and password
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if(optionalUser.isEmpty() || !BCrypt.checkpw(loginRequest.getPassword(), optionalUser.get().getPassword())){
            return null;
        }

        // Generate JWT and return
        User user = optionalUser.get();
        return new JwtToken(jwtTokenUtil.generateToken(user.getUsername(), ""));
    }

    public JwtToken generateJwtWithTenant(TenantRequest tenantRequest){

        if(tenantRequest.getJwt() == null || tenantRequest.getJwt().trim().equals("") || tenantRequest.getTenant() == null){
            return null;
        }
        try {
            String username = jwtTokenUtil.getUsernameFromToken(tenantRequest.getJwt());
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                Optional<TenantUser> optionalTenantUser = tenantUserRepository.findByTenantIdAndUserId(tenantRequest.getTenant(), user.getUserId());
                if(optionalTenantUser.isPresent()){
                    return new JwtToken(jwtTokenUtil.generateToken(username, tenantRequest.getTenant().toString()));
                }else{
                    return null;
                }
            }else {
                return null;
            }
        } catch(Exception e){
            return null;
        }
    }

    public List<Tenant> getTenants(String jwtToken){
        return userRepository.findTenants(jwtTokenUtil.getUsernameFromToken(jwtToken));
    }

    public User getUser(String jwtToken) throws Exception{
        User user =  userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken)).orElseThrow(() -> new Exception("User Not Found"));
        user.setPassword("");
        return user;
    }
}
