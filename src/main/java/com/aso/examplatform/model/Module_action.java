package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module_action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long module_action_id;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Modules modules;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Actions action;
}
