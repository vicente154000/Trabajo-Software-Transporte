package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import dao.ListaEjercicioEntrenamiento;
import dao.ListaUsuarios;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.ListaUsuarios;
import modelo.Usuario;
import services.UbicacionService;
import services.UsuarioService;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private Button loginButton;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.usuarioService = ((LayerApplication)getApplicationContext()).getUsuarioService();

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(gfgPolicy);

        nameInput = findViewById(R.id.nameInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            // Vemos que el nombre no este faltante.
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(MainActivity.this, "Por favor, introduce tu nombre.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Conseguimos los usuarios de Back4App
            List<Usuario> usuarios = usuarioService.getListaUsuarios();

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
                String objectId = usuarioService.addUsuario(newUser);
                newUser.setId(objectId);
            }

            // Pasamos a la siguiente activity con el nombre de usuario.
            Intent intent = new Intent(MainActivity.this, IntensityActivity.class);
            intent.putExtra("nombreUsuario", name);
            startActivity(intent);
        });
    }
}