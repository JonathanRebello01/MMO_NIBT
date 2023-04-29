package com.example.mmonibt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SelecaoOracao extends AppCompatActivity {

    private CheckBox checkBox_ora_seg,checkBox_ora_ter,checkBox_ora_qua,checkBox_ora_qui,checkBox_ora_sex;

    private Button bt_enviar_ora;

    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_oracao);
        getSupportActionBar().hide();
        int preto = ContextCompat.getColor(this, R.color.black);
        Window window = getWindow();
        window.setStatusBarColor(preto);
        IniciarComponentes();

        bt_enviar_ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalvarOracaoUsuario();
                TipoTabela();
            }
        });
    }

    private void TipoTabela(){
        Intent intent = new Intent(SelecaoOracao.this,TipoTabela.class);
        startActivity(intent);
    }

    private void SalvarOracaoUsuario(){
        Boolean ora_seg = checkBox_ora_seg.isChecked();
        Boolean ora_ter = checkBox_ora_ter.isChecked();
        Boolean ora_qua = checkBox_ora_qua.isChecked();
        Boolean ora_qui = checkBox_ora_qui.isChecked();
        Boolean ora_sex = checkBox_ora_sex.isChecked();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Boolean> oracao = new HashMap<>();
        oracao.put("Segunda", ora_seg);
        oracao.put("terça", ora_ter);
        oracao.put("quarta", ora_qua);
        oracao.put("quinta", ora_qui);
        oracao.put("sexta", ora_sex);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Orações").document(usuarioID);

        documentReference.set(oracao).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    private void IniciarComponentes(){
        checkBox_ora_seg = findViewById(R.id.checkBox_ora_seg);
        checkBox_ora_ter = findViewById(R.id.checkBox_ora_ter);
        checkBox_ora_qua = findViewById(R.id.checkBox_ora_qua);
        checkBox_ora_qui = findViewById(R.id.checkBox_ora_qui);
        checkBox_ora_sex = findViewById(R.id.checkBox_ora_sex);
        bt_enviar_ora = findViewById(R.id.bt_enviar_ora);
    }
}