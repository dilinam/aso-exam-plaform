package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tenantId;
    private String tenantName;
    private String description;

    @Column(columnDefinition="tinyint(1)")
    private boolean status = true;

    @Column(columnDefinition="tinyint(1)")
    private boolean deleted = false;

    private String createdBy;
    private long createdAt;

    private String updatedBy;
    private long updatedAt;
}
