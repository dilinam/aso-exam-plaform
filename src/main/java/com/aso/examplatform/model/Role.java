package com.aso.examplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private Long roleId;

    @Column(nullable = false, length = 100, name = "role_name")
    @NotBlank(message = "Role name must be required.")
    private String roleName;

    @ManyToMany
    @JsonIgnore
    private List<ModuleAction> moduleActions;
}
