package com.aso.examplatform.model;

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
public class ModuleAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long moduleActionId;

    @Column(nullable = false, length = 100, name = "module_action_end_point")
    @NotBlank(message = "End point name must be required.")
    private String endPoint;

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
