package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, length = 100, name = "username")
    @NotBlank(message = "User name must be required.")
    private String username;

    @Column(nullable = false,name = "password",columnDefinition="TEXT")
    private String password;

    @Column(nullable = false, length = 100, name = "first_name")
    @NotBlank(message = "First name must be required.")
    private String firstName;

    @Column(nullable = false, length = 100, name = "last_name")
    @NotBlank(message = "Last name must be required.")
    private String lastName;

    @Column(nullable = false, length = 100, name = "nic")
    @NotBlank(message = "NIC must be required.")
    private String nic;

    @Column(nullable = false, length = 100, name = "address")
    @NotBlank(message = "Address must be required.")
    private String address;
    private String contactNo;
    private String email;
    private Long dob;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status = true;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean deleted;

    private boolean superAdmin;

    @CreationTimestamp
    private Timestamp createdAt;
    private String createdBy;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", status=" + status +
                ", deleted=" + deleted +
                ", superAdmin=" + superAdmin +
                '}';
    }
}
