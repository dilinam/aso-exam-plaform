package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role_module_action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_module_action;

    @ManyToMany
    @JoinColumn(name = "role_id")
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "module_action_id")
    private Module_action module_action;
}
