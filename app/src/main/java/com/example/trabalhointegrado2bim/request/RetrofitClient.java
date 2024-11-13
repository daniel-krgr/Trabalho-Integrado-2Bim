package com.example.trabalhointegrado2bim.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    // lembrar de trocar o ip do servidor
    // e colocar o ip na lista do network_security_config.xml
    private static final String BASE_URL = "http://192.168.1.107:3011";

    // Método para obter a instância Retrofit
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}