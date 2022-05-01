package com.aso.examplatform.service;

import com.aso.examplatform.dto.UserRequest;
import com.aso.examplatform.model.Role;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.RoleRepository;
import com.aso.examplatform.repository.TenantUserRepository;
import com.aso.examplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TenantUserRepository tenantUserRepository;
    private final RoleRepository roleRepository;

    public List<User> getUsers() {
        return userRepository.findAllByDeleted(false);
    }

    public User getUser(Long userId) {
        return userRepository.getById(userId);
    }

    public TenantUser addUser(UserRequest userRequest, Tenant tenant){
        userRequest.getUser().setPassword(BCrypt.hashpw(userRequest.getUser().getPassword(), BCrypt.gensalt()));
        if(userRepository.findByUsername(userRequest.getUser().getUsername()).isEmpty()){
            userRepository.save(userRequest.getUser());
        }
        Optional<Role> roleOptional = roleRepository.findById(userRequest.getRoleId());
        if(roleOptional.isEmpty()){
            return null;
        }
        TenantUser tenantUser = new TenantUser(tenant,userRequest.getUser(),roleOptional.get());
        return tenantUserRepository.save(tenantUser);
    }

    public User addSuperAdmin(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setSuperAdmin(true);
        userRepository.save(user);
        return user;
    }
}
