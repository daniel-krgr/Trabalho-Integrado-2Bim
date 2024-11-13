package com.example.trabalhointegrado2bim.request;

import java.util.Date;

public class CadastroUserRequestBody {
    private String nome;
    private String email;
    private String dNascimento;
    private String nPhone;
    private String senha;

    public CadastroUserRequestBody(String nome, String email, String dNascimento, String nPhone, String senha){
        this.nome = nome;
        this.email = email;
        this.dNascimento = dNascimento;
        this.nPhone = nPhone;
        this.senha = senha;
    }
}
