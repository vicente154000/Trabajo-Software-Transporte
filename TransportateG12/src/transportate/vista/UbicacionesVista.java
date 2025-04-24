/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.vista;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import transportate.modelo.Ubicacion;

/**
 *
 * @author ruben
 */
public class UbicacionesVista extends JPanel{
    private DefaultListModel<Ubicacion> modeloLista;
    private JList<Ubicacion> lista;
    private JButton btnAgregar;
    private JButton btnEliminar;

    public UbicacionesVista() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Ubicaciones"));

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

    public void mostrarUbicaciones(List<Ubicacion> ubicaciones) {
        modeloLista.clear();
        for (Ubicacion u : ubicaciones) {
            modeloLista.addElement(u);
        }
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public Ubicacion getSeleccionado() {
        return lista.getSelectedValue();
    }
}
