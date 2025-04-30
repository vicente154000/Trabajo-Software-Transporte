package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nameInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        nameInput = findViewById(R.id.nameInput);
        loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                //Toast.makeText(MainActivity.this, "Por favor, introduce tu nombre.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, IntensityActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, IntensityActivity.class);
                startActivity(intent);
            }
        });
    }

}
