package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModuleAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleModuleActionId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "module_action_id")
    private ModuleAction moduleAction;

}
