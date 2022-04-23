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

    @ManyToOne
    @JoinColumn(name = "actionId")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "moduleId")
    private Module module;

    @ManyToMany(mappedBy = "moduleActions")
    private List<Role> roles;

}
