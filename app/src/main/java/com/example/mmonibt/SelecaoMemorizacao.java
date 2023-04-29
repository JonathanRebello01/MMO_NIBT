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

public class SelecaoMemorizacao extends AppCompatActivity {

    private CheckBox checkBox_mem_seg,checkBox_mem_ter,checkBox_mem_qua,checkBox_mem_qui,checkBox_mem_sex;

    private Button bt_enviar_mem;

    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_memorizacao);
        getSupportActionBar().hide();
        IniciarComponentes();
        int preto = ContextCompat.getColor(this, R.color.black);
        Window window = getWindow();
        window.setStatusBarColor(preto);

       bt_enviar_mem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SalvarMemorizacaoUsuario();
               SelecaoMeditacao();
           }
            });
         }

        private void SelecaoMeditacao(){
           Intent intent = new Intent(SelecaoMemorizacao.this,SelecaoOracao.class);
           startActivity(intent);
         }

        public void SalvarMemorizacaoUsuario() {
         Boolean mem_seg = checkBox_mem_seg.isChecked();
         Boolean mem_ter = checkBox_mem_ter.isChecked();
         Boolean mem_qua = checkBox_mem_qua.isChecked();
         Boolean mem_qui = checkBox_mem_qui.isChecked();
         Boolean mem_sex = checkBox_mem_sex.isChecked();

          FirebaseFirestore db = FirebaseFirestore.getInstance();

          Map<String, Boolean> memorizacao = new HashMap<>();
          memorizacao.put("Segunda", mem_seg);
          memorizacao.put("terça", mem_ter);
         memorizacao.put("quarta", mem_qua);
          memorizacao.put("quinta", mem_qui);
          memorizacao.put("sexta", mem_sex);

         usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

          DocumentReference documentReference = db.collection("Memorizações").document(usuarioID);

          documentReference.set(memorizacao).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os dados");
            }
            }).addOnFailureListener(new OnFailureListener() {
              @Override
             public void onFailure(@NonNull Exception e) {
                  Log.d("db_error", "Erro ao salvar os dados" + e.toString());
               }
           });
           }

        private void IniciarComponentes(){
            checkBox_mem_seg = findViewById(R.id.checkBox_mem_seg);
            checkBox_mem_ter = findViewById(R.id.checkBox_mem_ter);
            checkBox_mem_qua = findViewById(R.id.checkBox_mem_qua);
            checkBox_mem_qui = findViewById(R.id.checkBox_mem_qui);
            checkBox_mem_sex = findViewById(R.id.checkBox_mem_sex);
            bt_enviar_mem = findViewById(R.id.bt_enviar_mem);
        }
    }
