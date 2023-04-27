package com.example.mmonibt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class FormCadastro extends AppCompatActivity {

private EditText edit_nome,edit_email,edit_senha,edit_lider_ga,edit_tel,edit_ministerio,edit_aniversario,edit_matricula;
private Button bt_cadastrar,bt_limpar;

private TextView title_cadastro;
String[] mensagens = {"Preencha todos os campos" , "Cadastro realizado com sucesso"};

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();
                String lider_ga = edit_lider_ga.getText().toString();
                String tel = edit_tel.getText().toString();
                String ministerio = edit_ministerio.getText().toString();
                String aniversario = edit_aniversario.getText().toString();
                String matricula = edit_matricula.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || lider_ga.isEmpty() || tel.isEmpty() || ministerio.isEmpty() || aniversario.isEmpty() || matricula.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    CadastrarUsuario();
                }
            }
        });
        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_nome.setText("");
                edit_email.setText("");
                edit_senha.setText("");
                edit_lider_ga.setText("");
                edit_tel.setText("");
                edit_ministerio.setText("");
                edit_aniversario.setText("");
                edit_matricula.setText("");

            }
        });

    }
    private void IniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        edit_lider_ga = findViewById(R.id.edit_lider_ga);
        edit_tel = findViewById(R.id.edit_tel);
        edit_ministerio = findViewById(R.id.edit_ministerio);
        edit_aniversario = findViewById(R.id.edit_aniversario);
        edit_matricula = findViewById(R.id.edit_matricula);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
        bt_limpar = findViewById(R.id.bt_limpar);
        title_cadastro = findViewById(R.id.title_cadastro);
    }

    private void CadastrarUsuario(){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();
    }

}