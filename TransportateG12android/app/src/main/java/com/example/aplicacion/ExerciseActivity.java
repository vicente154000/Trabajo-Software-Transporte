package com.example.aplicacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import modelo.Ejercicio;
import modelo.Entrenamiento;
import services.EntrenamientoService;
import services.UbicacionService;

public class ExerciseActivity extends AppCompatActivity {

    private Button btnStart, btnComplete, btnCancel, btnCamera, btnHelp, btnGps;
    private TextView textoEjercicio;
    private EditText textoNombreEjercicio;
    private boolean camaraCompletada = false;
    private boolean ejercicioEmpezado = false;
    private boolean llegadoAUbicacion = false;
    private static final int REQUEST_CAMARA = 101;
    private static final int REQUEST_MAP = 102;
    private List<Ejercicio> ejercicios;
    private int ejercicioActual = 0;
    UbicacionService ubicacionesService;
    EntrenamientoService entrenamientosService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        String intensidad = getIntent().getStringExtra("intensidad");

        this.ubicacionesService = ((LayerApplication)getApplicationContext()).getUbicacionService();
        this.entrenamientosService = ((LayerApplication)getApplicationContext()).getEntrenamientosService();

        btnStart = findViewById(R.id.btnStart);
        btnComplete = findViewById(R.id.btnComplete);
        btnCancel = findViewById(R.id.btnCancel);
        btnCamera = findViewById(R.id.btnCamera);
        btnHelp = findViewById(R.id.btnHelp);
        btnGps = findViewById(R.id.btnGps);
        textoNombreEjercicio = findViewById(R.id.nombreEjercicio);
        textoEjercicio = findViewById(R.id.descripcionEjercicio);

        //Listener para botón Cámara
        btnCamera.setOnClickListener(v -> {
            if (!ejercicioEmpezado) {
                Toast.makeText(this, "Primero debes empezar el ejercicio", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, CamaraActivity.class);
            startActivityForResult(intent, REQUEST_CAMARA);
        });

        //Listener para botón EMPEZAR
        btnStart.setOnClickListener(v -> {
            if(!llegadoAUbicacion) {
                Toast.makeText(this, "Primero debes llegar a la ubicación del ejercicio", Toast.LENGTH_SHORT).show();
                return;
            }

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
            AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_cancelar_ejercicio, null);
            builder.setView(dialogView);

            AlertDialog alertDialog = builder.create();

            Button btnAceptar = dialogView.findViewById(R.id.btnAceptarDialog);
            Button btnCancelar = dialogView.findViewById(R.id.btnCancelarDialog);

            btnAceptar.setOnClickListener(view -> {
                Toast.makeText(ExerciseActivity.this, "Ejercicio y entrenamiento cancelados", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                finish(); // Finaliza la actividad actualz
            });

            btnCancelar.setOnClickListener(view -> alertDialog.dismiss());

            alertDialog.show();
        });
        // Listener para botón AYUDA
        btnHelp.setOnClickListener(v -> {
            modelo.Ejercicio ejercicio = ejercicios.get(ejercicioActual);
            System.out.println("Ejercicio actual: " + ejercicio.getNombre());
            System.out.println("URL de la imagen: " + ejercicio.getImagen());
            String imageURL = ejercicio.getImagen();

            Intent intent = new Intent(ExerciseActivity.this, AyudaActivity.class);
            intent.putExtra("imageURL", imageURL);

            startActivity(intent);
        });

        Entrenamiento entrenamientoElegido = entrenamientosService.obtenerEntrenamientoAleatorioPorTipo(intensidad);

        ejercicios = entrenamientosService.obtenerEjerciciosPorEntrenamiento(entrenamientoElegido);


        if (ejercicios == null || ejercicios.isEmpty()) {
            Toast.makeText(this, "No hay ejercicios disponibles", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        btnGps.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapActivity.class);
            String idUbicacion = ubicacionesService.getIdUbicacionPorEjercicio(ejercicios.get(ejercicioActual).getId());
            intent.putExtra("lat_dest", ubicacionesService.getLatitudPorUbicacion(idUbicacion));
            intent.putExtra("lon_dest", ubicacionesService.getLongitudPorUbicacion(idUbicacion));
            intent.putExtra("nombre_dest", ubicacionesService.getNombrePorUbicacion(idUbicacion));
            startActivityForResult(intent, REQUEST_MAP);
        });

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

        if (requestCode == REQUEST_MAP && resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            if ("llegado".equals(result)) {
                llegadoAUbicacion = true;
                btnGps.setText("✔ Completado!");
                btnGps.setEnabled(false);
            }
        }
    }

    private void mostrarEjercicioActual() {
        modelo.Ejercicio ejercicio = ejercicios.get(ejercicioActual);
        setTitle(ejercicio.getNombre()); // También puedes usar TextViews para mostrar más detalles
        btnStart.setText("Empezar");
        btnCamera.setText("Cámara");
        btnCamera.setEnabled(true);
        btnGps.setText("GPS");
        btnGps.setEnabled(true);
        camaraCompletada = false;
        ejercicioEmpezado = false;
        llegadoAUbicacion = false;
        textoEjercicio.setText(ejercicio.getDescripcion());
        textoNombreEjercicio.setText(ejercicio.getNombre());
        System.out.println("Ejercicio actual: " + ejercicio.getNombre());
    }
}
