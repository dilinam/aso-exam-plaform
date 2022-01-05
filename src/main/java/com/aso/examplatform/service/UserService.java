package com.aso.examplatform.service;

import com.aso.examplatform.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByID(Long userId);
}
