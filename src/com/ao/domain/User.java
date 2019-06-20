package com.ao.domain;

/**
 *    use for user table;
 *    实体类
 */

public class User {

    //private  int id;
    private String username;
    private String userpassword;

    public User(){}

    public User(String username,String userpassword){
        this.username=username;
        this.userpassword=userpassword;
    }

    public String getUsername() {
        return username;
    }

    public String getUserpassword() {
        return userpassword;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", userPassword='" + userpassword + '\'' +
                '}';
    }
}
