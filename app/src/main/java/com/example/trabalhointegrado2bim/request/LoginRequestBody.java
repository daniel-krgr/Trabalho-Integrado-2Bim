package com.example.trabalhointegrado2bim.request;

public class LoginRequestBody {
    private String usuario;
    private String senha;

    public LoginRequestBody(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }
}
