package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long actionId;

    @Column(nullable = false, length = 100, name = "action_name")
    @NotBlank(message = "Action name must be required.")
    private String actionName;
}
