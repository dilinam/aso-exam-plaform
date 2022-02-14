package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role_module_action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_module_action;

    public Role_module_action(Long role_module_action) {
        this.role_module_action = role_module_action;
    }

    public Long getRole_module_action() {
        return role_module_action;
    }

    public void setRole_module_action(Long role_module_action) {
        this.role_module_action = role_module_action;
    }

    @ManyToMany
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @JoinColumn(name = "module_action_id")
    private Module_action module_action;
}
