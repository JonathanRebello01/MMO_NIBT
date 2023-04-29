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

public class SelecaoMeditacao extends AppCompatActivity {

    private CheckBox checkBox_med_seg,checkBox_med_ter,checkBox_med_qua,checkBox_med_qui,checkBox_med_sex;

    private Button bt_enviar_med;

    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_meditacao);
        getSupportActionBar().hide();
        int preto = ContextCompat.getColor(this, R.color.black);
        Window window = getWindow();
        window.setStatusBarColor(preto);
        IniciarComponentes();

    bt_enviar_med.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SalvarMeditacaoUsuario();
            SelecaoMemorizacao();
        }
    });

    }
    public void SalvarMeditacaoUsuario(){
        Boolean med_seg = checkBox_med_seg.isChecked();
        Boolean med_ter = checkBox_med_ter.isChecked();
        Boolean med_qua = checkBox_med_qua.isChecked();
        Boolean med_qui = checkBox_med_qui.isChecked();
        Boolean med_sex = checkBox_med_sex.isChecked();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Boolean> meditacao = new HashMap<>();
        meditacao.put("Segunda", med_seg);
        meditacao.put("terça", med_ter);
        meditacao.put("quarta", med_qua);
        meditacao.put("quinta", med_qui);
        meditacao.put("sexta", med_sex);


        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Meditações").document(usuarioID);

        documentReference.set(meditacao).addOnSuccessListener(new OnSuccessListener<Void>() {
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
    private void SelecaoMemorizacao(){
        Intent intent = new Intent(SelecaoMeditacao.this,SelecaoMemorizacao.class);
        startActivity(intent);
        finish();
    }
    private void IniciarComponentes(){
        checkBox_med_seg = findViewById(R.id.checkBox_med_seg);
        checkBox_med_ter = findViewById(R.id.checkBox_med_ter);
        checkBox_med_qua = findViewById(R.id.checkBox_med_qua);
        checkBox_med_qui = findViewById(R.id.checkBox_med_qui);
        checkBox_med_sex = findViewById(R.id.checkBox_med_sex);
        bt_enviar_med = findViewById(R.id.bt_enviar_med);
    }
}

