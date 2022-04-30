package com.aso.examplatform.service;

import com.aso.examplatform.dto.UserRequest;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.TenantUserRepository;
import com.aso.examplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TenantUserRepository tenantUserRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.getById(userId);
    }

    public TenantUser addUser(UserRequest userRequest, Tenant tenant){
        userRequest.getUser().setPassword(BCrypt.hashpw(userRequest.getUser().getPassword(), BCrypt.gensalt()));
        if(userRepository.findByUsername(userRequest.getUser().getUsername()).isEmpty()){
            userRepository.save(userRequest.getUser());
        }
        TenantUser tenantUser = new TenantUser(tenant,userRequest.getUser(),userRequest.getRole());
        return tenantUserRepository.save(tenantUser);
    }
}
