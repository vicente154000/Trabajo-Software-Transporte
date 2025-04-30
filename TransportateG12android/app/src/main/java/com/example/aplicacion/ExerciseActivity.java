package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {

    private Button btnStart, btnComplete, btnCancel, btnCamera;
    private boolean camaraCompletada = false;
    private boolean ejercicioEmpezado = false;
    private static final int REQUEST_CAMARA = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        btnStart = findViewById(R.id.btnStart);
        btnComplete = findViewById(R.id.btnComplete);
        btnCancel = findViewById(R.id.btnCancel);
        btnCamera = findViewById(R.id.btnCamera);

        //Listener para botón Cámara
        btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(this, CamaraActivity.class);
            startActivityForResult(intent, REQUEST_CAMARA);
        });

        //Listener para botón EMPEZAR
        btnStart.setOnClickListener(v -> {
            ejercicioEmpezado = true; // Marcar ejercicio como empezado
            btnStart.setText("Empezado"); // Cambiar texto del botón
            Toast.makeText(this, "Ejercicio iniciado", Toast.LENGTH_SHORT).show();
        });

        //Listener para botón COMPLETAR
        btnComplete.setOnClickListener(v ->
                Toast.makeText(this, "Ejercicio completado. Pasando al siguiente.", Toast.LENGTH_SHORT).show());

        //Listener para botón CANCELAR
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Ejercicio cancelado", Toast.LENGTH_SHORT).show();
            finish(); //Termina esta actividad
        });
    }

    //Recibir resultado desde CamaraActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMARA && resultCode == RESULT_OK && data != null) {
            boolean completado = data.getBooleanExtra("camaraCompletado", false);
            if (completado) {
                camaraCompletada = true;
                btnCamera.setText("Completado");
            }
        }
    }
}
