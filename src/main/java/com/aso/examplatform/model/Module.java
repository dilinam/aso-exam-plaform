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
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long moduleId;

    @Column(nullable = false, length = 100, name = "module_name")
    @NotBlank(message = "Module name must be required.")
    private String moduleName;

}
