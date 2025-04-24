/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.vista;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import transportate.modelo.Ejercicio;
import transportate.modelo.Ubicacion;

/**
 *
 * @author ruben
 */
public class NuevaUbicacionDialog extends JDialog{
    private JTextField txtNombre;
    private JTextField txtLatitud;
    private JTextField txtLongitud;
    private JButton btnGuardar;
    private Ubicacion ubicacionCreada;

    public NuevaUbicacionDialog(JFrame parent) {
        super(parent, "Nueva Ubicacion", true);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        txtNombre = new JTextField();
        txtLatitud = new JTextField();
        txtLongitud = new JTextField();
        btnGuardar = new JButton("Guardar");

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Latitud:"));
        add(txtLatitud);
        add(new JLabel("Longitud:"));
        add(txtLongitud);
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                double latitud = Float.parseFloat(txtLatitud.getText());
                double longitud = Float.parseFloat(txtLongitud.getText());

                ubicacionCreada = new Ubicacion(nombre, latitud, longitud);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Revisa los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(parent);
    }

    public Ubicacion getUbicacionCreada() {
        return ubicacionCreada;
    }
}
