package com.example.Insurance_Aggregator_Project.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;

   @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserBuilder builder(){
    return new UserBuilder();
}

   public static class UserBuilder{

       private Long id;

       private String email;

       private String password;

       private UserBuilder(){

       }

       public UserBuilder email(String email){
           this.email = email;
           return this;
       }


       public User build(){
           return new User(email,"");
       }

   }
}
