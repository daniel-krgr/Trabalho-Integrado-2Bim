package com.example.trabalhointegrado2bim;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabalhointegrado2bim.request.ApiService;
import com.example.trabalhointegrado2bim.request.LoginRequestBody;
import com.example.trabalhointegrado2bim.request.LoginResponse;
import com.example.trabalhointegrado2bim.request.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView tvCadastro;
    private EditText edEmail;
    private EditText edSenha;
    private Button btEntrar;
    String[] mensagem = {"preencha todos os campos",
            "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IniciarComponentes();

        // click no botão efetuar log-in
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edEmail.getText().toString();
                String senha = edSenha.getText().toString();

                // verifica se foi digitado valores nos campos

                if (email.isEmpty() || senha.isEmpty()){

                    mostrarSnackbar(view, mensagem[0], Color.WHITE, Color.BLACK);

                }else{
                    // faz verificação dos dados no servidor
                    fazerLogin(view, email, senha);

                }

            }
        });


        // click no botão criar uma conta
        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void IniciarComponentes() {
        tvCadastro = findViewById(R.id.tvCadastro);
        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        btEntrar = findViewById(R.id.btEntrar);
    }

    private void mostrarSnackbar(View view, String mensagem, int backgroundColor, int textColor) {
        Snackbar snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(backgroundColor);
        snackbar.setTextColor(textColor);
        snackbar.show();
    }


    private void fazerLogin(View view, String usuario, String senha) {
        // Configura o Retrofit e chama o endpoint de login
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        LoginRequestBody loginRequest = new LoginRequestBody(usuario, senha);

        // Executa a chamada assíncrona
        apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean success = "true".equalsIgnoreCase(response.body().getStatus());
                    if (success) {
                        mostrarSnackbar(view, mensagem[1], Color.GREEN, Color.WHITE);

                        // Redireciona para a tela principal após o login bem-sucedido
                        //Intent intent = new Intent(LoginActivity.this, TelaPrincipalActivity.class);
                        //startActivity(intent);
                        //finish();
                        Intent intent = new Intent(LoginActivity.this, CalendarioActivity.class);
                        startActivity(intent);

                    } else {
                        mostrarSnackbar(view, "Falha no login", Color.RED, Color.WHITE);
                    }
                } else {
                    mostrarSnackbar(view, "Erro na resposta do servidor", Color.RED, Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mostrarSnackbar(view, "Erro na requisição: " + t.getMessage(), Color.RED, Color.WHITE);
            }
        });
    }

}