package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import dao.ListaUsuarios;
import modelo.Usuario;

public class UsuarioActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private TextView textViewNombre;
    private Button buttonAtras, buttonGuardar;
    private ListaUsuarios listaUsuarios;
    private String originalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        // Referencias
        textViewNombre = findViewById(R.id.textViewNombre); // Título con el nombre actual
        editTextNombre = findViewById(R.id.editTextNombre); // Campo editable (vacío)
        buttonAtras = findViewById(R.id.buttonAtras);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        listaUsuarios = new ListaUsuarios(
                "PZhlOWsdhQur0CyiDdUTnjofkAnKCFu6tJyymCpM",
                "plmowUHRNjWQ5tCW85rr26EWeu3RW44c6lAJAgGe"
        );

        // Nombre que viene del Intent
        originalName = getIntent().getStringExtra("nombreUsuario");
        if (originalName != null) {
            textViewNombre.setText(originalName);     // Mostrar el nombre actual arriba
            editTextNombre.setText("");               // Campo vacío para escribir uno nuevo
        }

        // Botón Atrás
        buttonAtras.setOnClickListener(v -> finish());

        // Botón Guardar
        buttonGuardar.setOnClickListener(v -> {
            String newName = editTextNombre.getText().toString().trim();
            if (TextUtils.isEmpty(newName)) {
                Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (newName.equals(originalName)) {
                Toast.makeText(this, "No hay cambios para guardar.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Buscar y actualizar el usuario
            List<Usuario> usuarios = listaUsuarios.getListaUsuarios();
            Usuario found = null;
            for (Usuario u : usuarios) {
                if (originalName.equalsIgnoreCase(u.getNombre())) {
                    found = u;
                    break;
                }
            }

            if (found != null) {
                found.setNombre(newName);
                listaUsuarios.updateUsuario(found);

                new android.app.AlertDialog.Builder(this)
                        .setTitle("Nombre actualizado")
                        .setMessage("Tu nuevo nombre ha sido guardado correctamente.")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", (dialog, which) -> {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("nombreUsuario", newName);
                            setResult(RESULT_OK, resultIntent);
                            finish(); // cerrar actividad solo tras aceptar el diálogo
                        })
                        .show();
            } else {
                Toast.makeText(this, "Usuario no encontrado en la base de datos.", Toast.LENGTH_SHORT).show();
                finish(); // también cerramos si no se encontró el usuario
            }
        });
    }
}