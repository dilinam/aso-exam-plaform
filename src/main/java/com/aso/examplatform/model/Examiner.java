package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examiner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examinerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false,length = 100, name = "first_name")
    @NotBlank(message = "First name must be required.")
    private String firstName;

    @Column(nullable = false,length = 100, name = "last_name")
    @NotBlank(message = "Last name must be required.")
    private String lastName;

    @Column(nullable = false,length = 100, name = "nic")
    @NotBlank(message = "NIC must be required.")
    @UniqueElements(message = "NIC exists.")
    private String nic;

    @Column(nullable = false,length = 100, name = "address")
    @NotBlank(message = "Address must be required.")
    private String address;

    @Column(nullable = false,length = 100, name = "contact_number")
    @NotBlank(message = "Contact Number must be required.")
    private String contactNumber;

    @Column(nullable = false,length = 100, name = "email")
    @NotBlank(message = "Email must be required.")
    @UniqueElements(message = "email exists.")
    private String email;

    @Column(nullable = false, name = "dob")
    @NotNull(message = "DOB must be required.")
    private Long DOB;

    @Override
    public String toString() {
        return "Examiner{" +
                "examiner_id=" + examinerId +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                '}';
    }
}
