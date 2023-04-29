package com.example.mmonibt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class TipoTabela extends AppCompatActivity {

    Button bt_ga, bt_ministerio;

    boolean tabela_ga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_tabela);
        getSupportActionBar().hide();
        int preto = ContextCompat.getColor(this, R.color.black);
        Window window = getWindow();
        window.setStatusBarColor(preto);
        IniciarComponentes();

        bt_ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            tabela_ga = true;
            ExibeTabela();
            }
        });

        bt_ministerio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabela_ga = false;
                ExibeTabela();
            }
        });

    }

    private void ExibeTabela(){
        Intent intent = new Intent(TipoTabela.this,ExibeTabela.class);
        startActivity(intent);
    }

    private void IniciarComponentes(){
        bt_ga = findViewById(R.id.bt_ga);
        bt_ministerio = findViewById(R.id.bt_ministerio);
    }
}