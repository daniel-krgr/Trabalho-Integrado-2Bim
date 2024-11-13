package com.example.trabalhointegrado2bim.request;

public class LoginRequestBody {
    private String email;
    private String senha;

    public LoginRequestBody(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
