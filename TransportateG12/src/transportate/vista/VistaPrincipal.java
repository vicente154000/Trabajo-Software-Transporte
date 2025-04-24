package transportate.vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

    public VistaPrincipal(EjerciciosVista ejerciciosVista, EntrenamientosVista entrenamientosVista, UbicacionesVista ubicacionesVista) {
        setTitle("Panel de Control - Entrenamientos y Ejercicios");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(1, 2));
        add(ejerciciosVista);
        add(entrenamientosVista);
        add(ubicacionesVista);
    }
}
