package com.example.aplicacion;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.ArrayList;

import modelo.Ejercicio;
import modelo.Entrenamiento;
import services.EntrenamientoService;

public class IntensityActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    String nombreUsuario; // Para trackear al usuario.
    Button btnAlta, btnMedia, btnBaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intensity);

        //Drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nombreUsuario = getIntent().getStringExtra("nombreUsuario");
        //Menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_progreso) {
                Intent intent = new Intent(IntensityActivity.this, ProgresoActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                startActivity(intent);
            } else if (id == R.id.nav_usuario) {
                // Go to UsuarioActivity, passing the username
                Intent intent = new Intent(IntensityActivity.this, UsuarioActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                startActivityForResult(intent, 1);
            } else if (id == R.id.nav_estadisticas) {
                // Go to EstadisticasActivity, passing the username
                Intent intent = new Intent(IntensityActivity.this, EstadisticasActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                startActivity(intent);
            }
            drawerLayout.closeDrawers();
            return true;
        });

        //Botones intensidad
        btnAlta = findViewById(R.id.btnAlta);
        btnMedia = findViewById(R.id.btnMedia);
        btnBaja = findViewById(R.id.btnBaja);

        btnAlta.setOnClickListener(v -> launchExercise("alta"));
        btnMedia.setOnClickListener(v -> launchExercise("media"));
        btnBaja.setOnClickListener(v -> launchExercise("baja"));
    }

    private void launchExercise(String tipoIntensidad) {
        Intent intent = new Intent(IntensityActivity.this, ExerciseActivity.class);
        intent.putExtra("intensidad", tipoIntensidad);
        startActivity(intent);
    }

    // Actualizamos el nombre (ya que nos identifica, es importante).
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            nombreUsuario = data.getStringExtra("nombreUsuario");
        }
    }
}
