package com.example.aplicacion;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AyudaActivity extends AppCompatActivity {

    private Button buttonAtrasAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        
        buttonAtrasAyuda = findViewById(R.id.buttonAtrasAyuda);
        buttonAtrasAyuda.setOnClickListener(v -> finish());
    }
}
