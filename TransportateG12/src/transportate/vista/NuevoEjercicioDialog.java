/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.vista;

import transportate.modelo.Ejercicio;
import transportate.modelo.Ubicacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NuevoEjercicioDialog extends JDialog {

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtDuracion;
    private JTextField txtIntensidad;
    private JComboBox<Ubicacion> comboUbicaciones;
    private JButton btnGuardar;
    private Ejercicio ejercicioCreado;
    private Ubicacion ubicacionSeleccionada;

    public NuevoEjercicioDialog(JFrame parent, List<Ubicacion> ubicaciones) {
        super(parent, "Nuevo Ejercicio", true);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        txtNombre = new JTextField();
        txtDescripcion = new JTextField();
        txtDuracion = new JTextField();
        txtIntensidad = new JTextField();
        comboUbicaciones = new JComboBox<>(ubicaciones.toArray(new Ubicacion[0]));
        btnGuardar = new JButton("Guardar");

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Descripción:"));
        add(txtDescripcion);
        add(new JLabel("Duración (minutos):"));
        add(txtDuracion);
        add(new JLabel("Intensidad (1-5):"));
        add(txtIntensidad);
        add(new JLabel("Ubicación:"));
        add(comboUbicaciones);
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                int duracion = Integer.parseInt(txtDuracion.getText());
                int intensidad = Integer.parseInt(txtIntensidad.getText());
                Ubicacion ubicacion = (Ubicacion) comboUbicaciones.getSelectedItem();

                ejercicioCreado = new Ejercicio(nombre, descripcion, duracion, intensidad, ubicacion.getNombre());
                this.ubicacionSeleccionada = ubicacion;
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Revisa los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(parent);
    }

    public Ejercicio getEjercicioCreado() {
        return ejercicioCreado;
    }

    public Ubicacion getUbicacionSeleccionada() {
        return ubicacionSeleccionada;
    }
}
