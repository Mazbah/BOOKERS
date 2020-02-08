package com.example.user.bookers;



/**
 * Created by User on 19/06/2019.
 */

public class User {

    // define four String variables
    private String username,email, mobile_number, password;
    // generate their respective constructors
    public User(String username,String email, String mobile_number, String password) {
        this.username = username;
        this.email = email;
        this.mobile_number = mobile_number;
        this.password=password;
    }
    // create an empty constructor
    public User() {
    }
    public void setName(String name){
        this.username = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
    public void setPassword(String desc) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getMobile_number() {
        return mobile_number;
    }
    public String getPassword() {
        return password;
    }
    public String getusername(){
        return  username;
    }
}