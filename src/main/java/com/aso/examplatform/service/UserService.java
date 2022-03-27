package com.aso.examplatform.service;

import com.aso.examplatform.model.User;
import com.aso.examplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long userId) {
        return null;
    }
}
