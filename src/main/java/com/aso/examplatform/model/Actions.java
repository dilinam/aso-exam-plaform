package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long action_id;
    private String action_name;
}
