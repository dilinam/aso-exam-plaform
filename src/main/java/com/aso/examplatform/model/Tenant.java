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

    @Column
    private String tenantName;

    @Column
    private String tenantDescription;

    @Column
    private boolean status;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean deleted;
}
