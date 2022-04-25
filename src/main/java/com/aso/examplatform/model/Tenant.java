package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.*;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> origin/pasindu-dev

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
<<<<<<< HEAD

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tenantId;
    private String tenantName;
    private String description;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean deleted;

    private String createdBy;
    private String createdAt;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tenant_id;
    private String tenant_name;
    private String tenant_description;
    private String status;
>>>>>>> origin/pasindu-dev
}
