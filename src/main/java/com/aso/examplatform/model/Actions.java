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

    public Actions(Long action_id, String action_name) {
        this.action_id = action_id;
        this.action_name = action_name;
    }

    public Long getAction_id() {
        return action_id;
    }

    public void setAction_id(Long action_id) {
        this.action_id = action_id;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }
}
