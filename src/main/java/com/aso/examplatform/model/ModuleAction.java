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
public class ModuleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long moduleActionId;
    private String endPoint;
    private String httpMethod;

    @ManyToOne
    @JoinColumn(name = "actionId")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "moduleId")
    private Module module;

    @ManyToMany()
    @JoinTable(
            name = "module_action_role",
            joinColumns = @JoinColumn(name = "module_action_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
