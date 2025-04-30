import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacion.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dao.ListaEjercicios;
import dao.ListaEntrenamientos;
import dao.ListaUsuarios;
import modelo.Ejercicio;
import modelo.Entrenamiento;
import modelo.Ubicacion;
import modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Asociamos la UI (XML) a la Activity
    }
}
