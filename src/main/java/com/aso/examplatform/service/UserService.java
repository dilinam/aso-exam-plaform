package com.aso.examplatform.service;

import com.aso.examplatform.dto.UserRequest;
import com.aso.examplatform.model.*;
import com.aso.examplatform.repository.RoleRepository;
import com.aso.examplatform.repository.TenantUserCourseRepository;
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
    private final TenantUserCourseRepository tenantUserCourseRepository;
    private final TenantUserRepository tenantUserRepository;
    private final RoleRepository roleRepository;

    public List<User> getUsers() {
        return userRepository.findAllByDeleted(false);
    }

    public User getUser(Long userId)  throws Exception{
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new Exception("User not found"));
    }

    public TenantUserCourse getTenantUserCourse(Long userId) throws Exception{
        Optional<TenantUserCourse> optionalTenantUserCourse = tenantUserCourseRepository.findTenantUserCourseByUserId(userId);
        return optionalTenantUserCourse.orElseThrow(() -> new Exception("User not found"));
    }

    public TenantUser addUser(UserRequest userRequest, Tenant tenant){
        userRequest.getUser().setPassword(BCrypt.hashpw(userRequest.getUser().getPassword(), BCrypt.gensalt())); // hashing user password
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

    public User updateUser(User user) throws Exception{
        if (userRepository.findById(user.getUserId()).isEmpty()){
            throw new Exception("User not found");
        }
        userRepository.save(user);
        return user;
    }
    public void delete(Long id) throws Exception{
        Optional<User> userOptional= userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new Exception("User not found");
        }
        User user = userOptional.get();
        user.setDeleted(true);
        userRepository.save(user);
    }
}
