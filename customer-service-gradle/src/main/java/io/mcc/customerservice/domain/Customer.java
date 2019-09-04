package io.mcc.customerservice.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
//@EntityScan("com.package")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;


    private Date created_At;
    private Date updated_At;

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
}
