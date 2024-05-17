package com.example.cybookfx;


import java.util.*;
public class User {
    static int idCount = 0;
    private String firstName;
    private String lastName;
    private final int id;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = ++idCount;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
}