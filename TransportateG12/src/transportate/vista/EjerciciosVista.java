package transportate.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import transportate.modelo.Ejercicio;

public class EjerciciosVista extends JPanel {

    private DefaultListModel<Ejercicio> modeloLista;
    private JList<Ejercicio> lista;
    private JButton btnAgregar;
    private JButton btnEliminar;

    public EjerciciosVista() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Ejercicios"));

        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);

        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");

        JPanel botones = new JPanel();
        botones.add(btnAgregar);
        botones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public void mostrarEjercicios(List<Ejercicio> ejercicios) {
        modeloLista.clear();
        for (Ejercicio e : ejercicios) {
            modeloLista.addElement(e);
        }
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public Ejercicio getSeleccionado() {
        return lista.getSelectedValue();
    }
}
