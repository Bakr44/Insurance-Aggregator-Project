package com.example.Insurance_Aggregator_Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int policyNumber;
    private String provider;
    private String type;
    private double basePrice;
    private double tax;


    @JsonIgnore
    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<User> userList;

    public void setUser(User user) {

    }
}
