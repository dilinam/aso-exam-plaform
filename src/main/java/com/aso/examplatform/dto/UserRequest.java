package com.aso.examplatform.dto;

import com.aso.examplatform.model.Role;
import com.aso.examplatform.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    public User user;
    public Role role;
}
