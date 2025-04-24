package transportate.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import transportate.modelo.Entrenamiento;

public class EntrenamientosVista extends JPanel {

    private DefaultListModel<Entrenamiento> modeloLista;
    private JList<Entrenamiento> lista;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnEditarEjercicios;

    public EntrenamientosVista() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Entrenamientos"));

        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);

        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnEditarEjercicios = new JButton("Editar ejercicios");

        JPanel botones = new JPanel();
        botones.add(btnAgregar);
        botones.add(btnEliminar);
        botones.add(btnEditarEjercicios);

        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public void mostrarEntrenamientos(List<Entrenamiento> entrenamientos) {
        modeloLista.clear();
        for (Entrenamiento e : entrenamientos) {
            modeloLista.addElement(e);
        }
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    
    public JButton getBtnEditarEjercicios(){
        return btnEditarEjercicios;
    }

    public Entrenamiento getSeleccionado() {
        return lista.getSelectedValue();
    }
}
