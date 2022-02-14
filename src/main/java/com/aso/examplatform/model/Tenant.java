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
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tenant_id;
    private String tenant_name;
    private String tenant_description;
    private String status;

    public Tenant(Long tenant_id, String tenant_name, String tenant_description, String status) {
        this.tenant_id = tenant_id;
        this.tenant_name = tenant_name;
        this.tenant_description = tenant_description;
        this.status = status;
    }

    public Long getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Long tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }

    public String getTenant_description() {
        return tenant_description;
    }

    public void setTenant_description(String tenant_description) {
        this.tenant_description = tenant_description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
