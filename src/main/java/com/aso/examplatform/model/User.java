package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String nic;
    private String address;
    private String contact_no;
    private String email;
    private String dob;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean deleted;

    private String super_admin;

}
