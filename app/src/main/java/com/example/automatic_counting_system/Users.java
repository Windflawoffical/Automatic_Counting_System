package com.example.automatic_counting_system;
public class Users {
    public String name;
    public String email;
    public String password;
    public String address;

    public Users() {
    }

    public Users(String email, String password, String name, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
