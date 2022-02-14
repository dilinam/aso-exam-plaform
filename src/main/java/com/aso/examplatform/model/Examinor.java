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

    public Examinor(Long examinor_id, String first_name, String last_name, String nic, String address, String contact_no, String email, String dob, User user) {
        this.examinor_id = examinor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nic = nic;
        this.address = address;
        this.contact_no = contact_no;
        this.email = email;
        this.dob = dob;
        this.user = user;
    }

    public Long getExaminor_id() {
        return examinor_id;
    }

    public void setExaminor_id(Long examinor_id) {
        this.examinor_id = examinor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
