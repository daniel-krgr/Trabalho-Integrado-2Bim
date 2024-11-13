package com.example.trabalhointegrado2bim;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AtividadesActivity extends AppCompatActivity {

    private TextView tvSwitchCalendario;
    private FloatingActionButton btNovaAtividade;
    private AlertDialog dialog;
    private EditText etTitulo, etDescricao, etData;
    private View viewNovaAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_atividades);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvSwitchCalendario = findViewById(R.id.tvSwitchCalendario);
        btNovaAtividade = findViewById(R.id.btNovaAtividade);

        tvSwitchCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AtividadesActivity.this, CalendarioActivity.class);
                startActivity(intent);
            }
        });

        btNovaAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirNovaAtividade();
            }
        });
    }

    private void abrirNovaAtividade() {
        viewNovaAtividade = getLayoutInflater().inflate(R.layout.dialog_nova_atividade, null);

        etTitulo = viewNovaAtividade.findViewById(R.id.etTitulo);
        etDescricao = viewNovaAtividade.findViewById(R.id.etDescricao);
        etData = viewNovaAtividade.findViewById(R.id.etData);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setView(viewNovaAtividade);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Salvar", null);
        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btSalvar = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

                btSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarNovaAtividade();
                    }
                });
            }
        });
        dialog.show();
    }

    private void salvarNovaAtividade() {
        String titulo = etTitulo.getText().toString();
        String descricao = etDescricao.getText().toString();
        String data = etData.getText().toString();

        if (titulo.isEmpty()) {
            etTitulo.setError("Título é obrigatório");
            etTitulo.requestFocus();
            return;
        }
        if (descricao.isEmpty()) {
            etDescricao.setError("Descrição é obrigatória");
            etDescricao.requestFocus();
            return;
        }
        if (data.isEmpty()) {
            etData.setError("Data é obrigatória");
            etData.requestFocus();
            return;
        }

        Toast.makeText(this, "Atividade salva com sucesso", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}