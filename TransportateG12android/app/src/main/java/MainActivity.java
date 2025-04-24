package com.java;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.transportateg12.dao.ListaEjercicios;
import com.example.transportateg12.dao.ListaEntrenamientos;
import com.example.transportateg12.dao.ListaUsuarios;
import com.example.transportateg12.modelo.Ejercicio;
import com.example.transportateg12.modelo.Entrenamiento;
import com.example.transportateg12.modelo.Ubicacion;
import com.example.transportateg12.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Asociamos la UI (XML) a la Activity

        // Crear Ubicaciones
        Ubicacion aula221 = new Ubicacion("Aula 221", 40.7128, -74.0060);
        Ubicacion zonaSofas = new Ubicacion("Zona de sofás", 40.7135, -74.0055);

        // Crear ejercicios
        Ejercicio ejercicio1 = new Ejercicio(1, "Flexiones", "Ejercicio de fuerza", 10, 5, aula221);
        Ejercicio ejercicio2 = new Ejercicio(2, "Sentadillas", "Ejercicio de piernas", 15, 7, zonaSofas);

        // Crear lista de ejercicios y agregar ejercicios
        ListaEjercicios listaEjercicios = new ListaEjercicios();
        listaEjercicios.addEjercicio(ejercicio1);
        listaEjercicios.addEjercicio(ejercicio2);

        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan", "juan@example.com", "password123", 180, 25, 75);
        Usuario usuario2 = new Usuario("Maria", "maria@example.com", "contrasenia123", 165, 20, 53);

        // Crear lista de usuarios y agregar usuarios
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        listaUsuarios.addUsuario(usuario1);
        listaUsuarios.addUsuario(usuario2);

        // Crear entrenamiento
        List<Ejercicio> ejerciciosEntrenamiento = new ArrayList<>();
        ejerciciosEntrenamiento.add(ejercicio1);
        ejerciciosEntrenamiento.add(ejercicio2);
        Entrenamiento entrenamientoUsuario1 = new Entrenamiento(1, usuario1.getId(), 30, 200, new Date(), ejerciciosEntrenamiento);
        usuario1.agregarEntrenamientoHistorial(entrenamientoUsuario1);

        // Crear lista de entrenamientos y agregar entrenamiento
        ListaEntrenamientos listaEntrenamientos = new ListaEntrenamientos();
        listaEntrenamientos.addEntrenamiento(entrenamientoUsuario1);
    }
}
