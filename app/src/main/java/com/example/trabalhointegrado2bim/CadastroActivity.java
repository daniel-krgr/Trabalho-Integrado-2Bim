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
import com.example.trabalhointegrado2bim.request.CadastroUserRequestBody;
import com.example.trabalhointegrado2bim.request.CadastroUserResponse;
import com.example.trabalhointegrado2bim.request.RetrofitClient;
import com.example.trabalhointegrado2bim.util.HashUtil;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CadastroActivity extends AppCompatActivity {

    private EditText edNome, edEmil, edDataNascimento, edTelefone, edSenha, edSenhaNovamente;
    private Button btCadastrar;
    String[] mensagem = {"preencha todos os campos",
            "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // iniciar o metodo para instanciar os componentes
        IniciaComponentes();

        // verifica se foi clicado no botão
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edNome.getText().toString();
                String email = edEmil.getText().toString();
                String dataNascimento = edDataNascimento.getText().toString();
                String telefone = edTelefone.getText().toString();
                String senha = edSenha.getText().toString();
                String confirmaSenha = edSenhaNovamente.getText().toString();

                // verifica se foi informado os valores
                if (nome.isEmpty()
                        || email.isEmpty()
                        || dataNascimento.isEmpty()
                        || telefone.isEmpty()
                        || senha.isEmpty()
                        || confirmaSenha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(view,mensagem[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    // efetua o cadastro
                    fazerCadastro(view,nome,email,dataNascimento,telefone,senha);
                }

            }
        });

    }


    private void IniciaComponentes(){
        edNome = findViewById(R.id.edNome);
        edEmil = findViewById(R.id.edEmail);
        edDataNascimento = findViewById(R.id.edDataNascimento);
        edTelefone = findViewById(R.id.edTelefone);
        edSenha = findViewById(R.id.edSenha);
        edSenhaNovamente = findViewById(R.id.edSenhaNovamente);
        btCadastrar = findViewById(R.id.btCadastrar);
    }

    private void fazerCadastro(View view, String nome, String email, String dNascimento, String nPhone, String senha) {
        // converter a senha em um hash
        String senhaHashMD5 = HashUtil.toMD5(senha);
        // Configura o Retrofit e chama o endpoint de login
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        CadastroUserRequestBody cadastroUserRequest = new CadastroUserRequestBody(nome,email,dNascimento,nPhone,senhaHashMD5);

        // Executa a chamada assíncrona
        apiService.cadastro(cadastroUserRequest).enqueue(new Callback<CadastroUserResponse>() {
            @Override
            public void onResponse(Call<CadastroUserResponse> call, Response<CadastroUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean success = "true".equalsIgnoreCase(response.body().getStatus());
                    if (success) {
                        mostrarSnackbar(view, mensagem[1], Color.GREEN, Color.WHITE);

                        // Redireciona para a tela principal após o login bem-sucedido
                        //Intent intent = new Intent(LoginActivity.this, TelaPrincipalActivity.class);
                        //startActivity(intent);
                        //finish();
                        Intent intent = new Intent(CadastroActivity.this, CalendarioActivity.class);
                        startActivity(intent);

                    } else {
                        mostrarSnackbar(view, "Falha no login", Color.RED, Color.WHITE);
                    }
                } else {
                    mostrarSnackbar(view, "Erro na resposta do servidor", Color.RED, Color.WHITE);
                }
            }

            @Override
            public void onFailure(Call<CadastroUserResponse> call, Throwable t) {
                mostrarSnackbar(view, "Erro na requisição: " + t.getMessage(), Color.RED, Color.WHITE);
            }
        });
    }

    private void mostrarSnackbar(View view, String mensagem, int backgroundColor, int textColor) {
        Snackbar snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(backgroundColor);
        snackbar.setTextColor(textColor);
        snackbar.show();
    }

}