package com.rahul.authenticateusingspring.responseClasses;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class ResponseRegisterClass implements Serializable {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ResponseRegisterClass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "ResponseRegisterClass{" +
                        "username = '" + username + '\'' +
                        ",password = '" + password + '\'' +
                        "}";
    }
}