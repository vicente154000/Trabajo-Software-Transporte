package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import dao.ListaEjercicioEntrenamiento;
import dao.ListaUsuarios;

import java.util.List;

import dao.ListaUsuarios;
import modelo.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private Button loginButton;
    private ListaUsuarios listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LLamadas de red
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        nameInput = findViewById(R.id.nameInput);
        loginButton = findViewById(R.id.loginButton);
        listaUsuarios = new ListaUsuarios("PZhlOWsdhQur0CyiDdUTnjofkAnKCFu6tJyymCpM", "plmowUHRNjWQ5tCW85rr26EWeu3RW44c6lAJAgGe");

        loginButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            // Vemos que el nombre no este faltante.
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(MainActivity.this, "Por favor, introduce tu nombre.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Conseguimos los usuarios de Back4App
            List<Usuario> usuarios = listaUsuarios.getListaUsuarios();

            // Vemos si existe el usuario
            boolean userExists = false;
            for (Usuario u : usuarios) {
                if (name.equalsIgnoreCase(u.getNombre())) {
                    userExists = true;
                    break;
                }
            }

            // Si no existe, creamos el usuario
            if (!userExists) {
                Usuario newUser = new Usuario(name, "", "", 0, 0, 0);
                String objectId = listaUsuarios.addUsuario(newUser);
                newUser.setId(objectId);
            }

            // Pasamos a la siguiente activity con el nombre de usuario.
            Intent intent = new Intent(MainActivity.this, IntensityActivity.class);
            intent.putExtra("nombreUsuario", name);
            startActivity(intent);
        });
    }
}