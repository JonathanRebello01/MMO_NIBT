package com.example.mmonibt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SelecaoMeditacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_meditacao);
        getSupportActionBar().hide();
    }
}