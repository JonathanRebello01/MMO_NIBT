package com.example.mmonibt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {

private EditText edit_nome,edit_email,edit_senha,edit_lider_ga,edit_tel,edit_ministerio,edit_aniversario,edit_matricula;
private Button bt_cadastrar, bt_limpar;

private TextView title_cadastro;
String[] mensagens = {"Preencha todos os campos" , "Cadastro realizado com sucesso"};

String usuarioID;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();
        int preto = ContextCompat.getColor(this, R.color.black);
        Window window = getWindow();
        window.setStatusBarColor(preto);

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
                    CadastrarUsuario(v);
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

    private void CadastrarUsuario(View v){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    SalvarDadosUuario();
                    FormLogin();
                }
                else {
                    String erro;
                    try {
                        throw  task.getException();
                        }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no mínimo 6 caracteres";
                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "essa conta já foi cadastrada";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mai inválido";
                    } catch (Exception e) {
                        erro = "Deu ruim e nem eu sei o que aconteceu";
                    }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }

    private void SalvarDadosUuario(){
        String nome = edit_nome.getText().toString();
        String lider_ga = edit_lider_ga.getText().toString();
        String tel = edit_tel.getText().toString();
        String ministerio = edit_ministerio.getText().toString();
        String aniversario = edit_aniversario.getText().toString();
        String matricula = edit_matricula.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        usuarios.put("lider_ga", lider_ga);
        usuarios.put("tel", tel);
        usuarios.put("ministerio", ministerio);
        usuarios.put("aniversário", aniversario);
        usuarios.put("matricula", matricula);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);

        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao salvar os dados");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error","Erro ao salvar os dados" + e.toString());
            }
        });
    }

    private void FormLogin(){
        Intent intent = new Intent(FormCadastro.this,FormLogin.class);
        startActivity(intent);
        finish();
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



}