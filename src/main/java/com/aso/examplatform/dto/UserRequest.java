package com.aso.examplatform.dto;

import com.aso.examplatform.model.Role;
import com.aso.examplatform.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Valid
    public User user;
    @NotNull
    public Long roleId;
}
