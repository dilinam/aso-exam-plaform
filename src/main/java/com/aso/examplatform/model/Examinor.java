package com.aso.examplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examinor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examinor_id;
    private String first_name;
    private String last_name;
    private String nic;
    private String address;
    private String contact_no;
    private String email;
    private String dob;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
