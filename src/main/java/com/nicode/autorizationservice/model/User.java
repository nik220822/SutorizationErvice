package com.nicode.autorizationservice.model;

import com.nicode.autorizationservice.authorities.Authorities;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
public class User {

    private final List<Authorities> userAuthorities;
    @NotEmpty(message = "U forgot to enter the name")
    @Size(min = 5, max = 6, message = "The name characters number is incorrect")
    private final String name;
    @NotEmpty(message = "U forgot to enter the password")
    @Size(min = 10, max = 20, message = "The password characters number is incorrect")
    private final String password;


    public User(String name, String password, List<Authorities> userAuthorities) {
        this.name = name;
        this.password = password;
        this.userAuthorities = userAuthorities;
    }

    public List<Authorities> getUserAuthorities() {
        return this.userAuthorities;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
