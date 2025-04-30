package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import modelo.Ejercicio;

public class ExerciseActivity extends AppCompatActivity {

    private Button btnStart, btnComplete, btnCancel, btnCamera;
    private TextView textoEjercicio;
    private EditText textoNombreEjercicio;
    private boolean camaraCompletada = false;
    private boolean ejercicioEmpezado = false;
    private static final int REQUEST_CAMARA = 101;
    private ArrayList<Ejercicio> ejercicios;
    private int ejercicioActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        int intensidad = getIntent().getIntExtra("intensidad", -1);

        btnStart = findViewById(R.id.btnStart);
        btnComplete = findViewById(R.id.btnComplete);
        btnCancel = findViewById(R.id.btnCancel);
        btnCamera = findViewById(R.id.btnCamera);
        textoNombreEjercicio = findViewById(R.id.nombreEjercicio);
        textoEjercicio = findViewById(R.id.descripcionEjercicio);

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
        btnComplete.setOnClickListener(v -> {
            if (!ejercicioEmpezado) {
                Toast.makeText(this, "Primero debes empezar el ejercicio", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!camaraCompletada) {
                Toast.makeText(this, "Debes completar la cámara antes", Toast.LENGTH_SHORT).show();
                return;
            }

            ejercicioActual++;

            if (ejercicioActual < ejercicios.size()) {
                Toast.makeText(this, "Ejercicio completado. Siguiente...", Toast.LENGTH_SHORT).show();
                mostrarEjercicioActual();
            } else {
                Toast.makeText(this, "¡Entrenamiento completado!", Toast.LENGTH_LONG).show();
                finish(); // Cierra la actividad
            }
        });

        //Listener para botón CANCELAR
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Ejercicio cancelado", Toast.LENGTH_SHORT).show();
            finish(); //Termina esta actividad
        });

        ejercicios = getIntent().getParcelableArrayListExtra("ejercicios");

        if (ejercicios == null || ejercicios.isEmpty()) {
            Toast.makeText(this, "No hay ejercicios disponibles", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        mostrarEjercicioActual();
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

    private void mostrarEjercicioActual() {
        modelo.Ejercicio ejercicio = ejercicios.get(ejercicioActual);
        setTitle(ejercicio.getNombre()); // También puedes usar TextViews para mostrar más detalles
        btnStart.setText("Empezar");
        btnCamera.setText("Cámara");
        camaraCompletada = false;
        ejercicioEmpezado = false;
        textoEjercicio.setText(ejercicio.getDescripcion());
        textoNombreEjercicio.setText(ejercicio.getNombre());
        System.out.println("Ejercicio actual: " + ejercicio.getNombre());
    }
}
