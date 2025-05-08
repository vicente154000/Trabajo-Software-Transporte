package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class AyudaActivity extends AppCompatActivity {

    private Button buttonAtrasAyuda;
    private ImageView imageViewInstruccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra("imageURL");
        System.out.println("URL de la imagen: " + imageURL);

        imageViewInstruccion = findViewById(R.id.imageViewInstruccion);

        if (imageURL != null && !imageURL.isEmpty()) {
            Glide.with(this)
                    .load(imageURL)
                    .into(imageViewInstruccion);
        }

        // Configurar el botón de retroceso
        buttonAtrasAyuda = findViewById(R.id.buttonAtrasAyuda);
        buttonAtrasAyuda.setOnClickListener(v -> finish());
    }
}
