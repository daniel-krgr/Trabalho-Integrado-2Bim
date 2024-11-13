package com.example.trabalhointegrado2bim.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer id;
    private String nome;
    private String email;
    private String dNascimento;
    private String telefone;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getdNascimento() {
        return dNascimento;
    }

    public void setdNascimento(String dNascimento) {
        this.dNascimento = dNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
