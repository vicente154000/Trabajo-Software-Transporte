package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class IntensityActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

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

        //Menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_progreso) {
                Toast.makeText(this, "Mi progreso", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_usuario) {
                Toast.makeText(this, "Usuario", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_estadisticas) {
                Toast.makeText(this, "Estadísticas", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawers();
            return true;
        });

        //Botones intensidad
        btnAlta = findViewById(R.id.btnAlta);
        btnMedia = findViewById(R.id.btnMedia);
        btnBaja = findViewById(R.id.btnBaja);

        btnAlta.setOnClickListener(v -> {
            Intent intent = new Intent(IntensityActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });

        btnMedia.setOnClickListener(v -> {
            Intent intent = new Intent(IntensityActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });

        btnBaja.setOnClickListener(v -> {
            Intent intent = new Intent(IntensityActivity.this, ExerciseActivity.class);
            startActivity(intent);
        });
    }
}
