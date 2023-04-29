package com.example.mmonibt;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button bt_entrar, bt_tabela;
    private ProgressBar progressbar;

    String[] mensagens = {"Preencha todos os campos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        getSupportActionBar().hide();
        int preto = ContextCompat.getColor(this, R.color.black);
        Window window = getWindow();
        window.setStatusBarColor(preto);

        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){

                Intent intent = new Intent(FormLogin.this,FormCadastro.class);
                startActivity(intent);

            }

        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else{
                    AutenticarUsuario(v);
                }
            }
        });

        bt_tabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TipoTabela();
            }
        });

    }



    private void AutenticarUsuario(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressbar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SelecaoMeditacao();
                        }
                    },1500);
                }else {
                    String erro;
                    try{
                        throw task.getException();
                    }
                    catch (Exception e){
                        erro = "Erro ao realizar log-in";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }

    //@Override
    //protected void onStart() {
        //super.onStart();
        //FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
         //if(usuarioAtual != null){
            //SelecaoMeditacao();
        //}
    //}

    private void SelecaoMeditacao(){
        Intent intent = new Intent(FormLogin.this,SelecaoMeditacao.class);
        startActivity(intent);
        finish();
    }

    private void TipoTabela(){
        Intent intent = new Intent(FormLogin.this,TipoTabela.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        bt_tabela = findViewById(R.id.bt_tabela);
        progressbar = findViewById(R.id.progressbar);
    }

}