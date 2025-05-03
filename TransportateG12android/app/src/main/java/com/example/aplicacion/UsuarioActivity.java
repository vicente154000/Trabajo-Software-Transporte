package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import dao.ListaUsuarios;
import modelo.Usuario;

public class UsuarioActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private Button buttonAtras, buttonGuardar;
    private ListaUsuarios listaUsuarios;
    private String originalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        // Llamas de red desde el hilo principal.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        editTextNombre = findViewById(R.id.editTextNombre);
        buttonAtras = findViewById(R.id.buttonAtras);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        listaUsuarios = new ListaUsuarios("PZhlOWsdhQur0CyiDdUTnjofkAnKCFu6tJyymCpM", "plmowUHRNjWQ5tCW85rr26EWeu3RW44c6lAJAgGe");

        // Cogemos el nombre que pasamos del activity anterior y lo mostramos
        originalName = getIntent().getStringExtra("nombreUsuario");
        if (originalName != null) {
            editTextNombre.setText(originalName);
        }

        // Botton atras: volvemos sin que se guarde ningun cambio.
        buttonAtras.setOnClickListener(v -> finish());

        // Boton guardar: el nombre que hayamos escrito en el TextEdit se guardara.
        buttonGuardar.setOnClickListener(v -> {
            String newName = editTextNombre.getText().toString().trim();
            if (TextUtils.isEmpty(newName)) {
                Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show();
                return;
            }
            // Si no hay cambio, salimos.
            if (newName.equals(originalName)) {
                Toast.makeText(this, "No hay cambios para guardar.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            // Encontramos el usuario y le cambiamos el nombre
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
                Toast.makeText(this, "Nombre actualizado.", Toast.LENGTH_SHORT).show();

                // Devolvemos el nuevo nombre a IntensityActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nombreUsuario", newName);
                setResult(RESULT_OK, resultIntent);
            } else {
                Toast.makeText(this, "Usuario no encontrado en la base de datos.", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
