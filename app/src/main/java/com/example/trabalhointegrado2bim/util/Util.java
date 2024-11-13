package com.example.trabalhointegrado2bim.util;

public class Util {
    public static String obterPrimeiroNome(String nomeCompleto) {
        if (nomeCompleto != null && !nomeCompleto.isEmpty()) {
            String[] partes = nomeCompleto.split(" ");
            return partes[0];  // Retorna a primeira parte do nome
        }
        return "";  // Retorna uma string vazia caso o nome completo seja nulo ou vazio
    }
}
