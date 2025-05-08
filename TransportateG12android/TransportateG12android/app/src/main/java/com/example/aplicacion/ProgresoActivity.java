package com.example.aplicacion;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProgresoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progreso);

        // Obtenemos el nombre del usuario desde el intent
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");

        // Buscamos el TextView y lo actualizamos
        TextView textViewProgreso = findViewById(R.id.textViewProgreso);
        if (nombreUsuario != null) {
            textViewProgreso.setText("Progreso de " + nombreUsuario);
        }

        // Botón Volver, para cerrar la actividad
        Button buttonVolver = findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(v -> finish());
    }
}
