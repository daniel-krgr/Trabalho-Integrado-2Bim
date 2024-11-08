package com.example.trabalhointegrado2bim;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

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

}