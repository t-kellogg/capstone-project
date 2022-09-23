package com.ey.models;

public class AuthenticationForm {

    private String username;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticationForm(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public AuthenticationForm() {
    }
}