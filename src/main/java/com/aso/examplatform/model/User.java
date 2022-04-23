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
    @Column(columnDefinition="TEXT")
    private String password;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String contactNo;
    private String email;
    private Long dob;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean deleted;

    private boolean superAdmin;

}
