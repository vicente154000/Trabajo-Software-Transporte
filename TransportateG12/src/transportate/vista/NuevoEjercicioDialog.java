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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class NuevoEjercicioDialog extends JDialog {

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtDuracion;
    private JTextField txtIntensidad;
    private JComboBox<Ubicacion> comboUbicaciones;
    private JButton btnGuardar;
    private Ejercicio ejercicioCreado;
    private Ubicacion ubicacionSeleccionada;
    private JButton btnSeleccionarImagen;
    private JLabel lblImagenSeleccionada;
    private File imagenLocal;

    public NuevoEjercicioDialog(JFrame parent, List<Ubicacion> ubicaciones) {
        super(parent, "Nuevo Ejercicio", true);
        setSize(500, 400);
        setLayout(new GridBagLayout());

        txtNombre = new JTextField(20);
        txtDescripcion = new JTextField(20);
        txtDuracion = new JTextField(20);
        txtIntensidad = new JTextField(20);
        comboUbicaciones = new JComboBox<>(ubicaciones.toArray(new Ubicacion[0]));
        btnGuardar = new JButton("Guardar");
        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        lblImagenSeleccionada = new JLabel("Ninguna imagen seleccionada");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0 - Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        // Fila 1 - Descripción
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        add(txtDescripcion, gbc);

        // Fila 2 - Duración
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Duración (minutos):"), gbc);
        gbc.gridx = 1;
        add(txtDuracion, gbc);

        // Fila 3 - Intensidad
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Intensidad (1-5):"), gbc);
        gbc.gridx = 1;
        add(txtIntensidad, gbc);

        // Fila 4 - Ubicación
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Ubicación:"), gbc);
        gbc.gridx = 1;
        add(comboUbicaciones, gbc);

        // Fila 5 - Botón seleccionar imagen
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Imagen:"), gbc);
        gbc.gridx = 1;
        add(btnSeleccionarImagen, gbc);

        // Fila 6 - Label imagen seleccionada
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Seleccionada:"), gbc);
        gbc.gridx = 1;
        add(lblImagenSeleccionada, gbc);

        // Fila 7 - Botón guardar
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnGuardar, gbc);

        setLocationRelativeTo(parent);

        btnSeleccionarImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagenLocal = fileChooser.getSelectedFile();
                try {
                    Files.readAllBytes(imagenLocal.toPath());
                    lblImagenSeleccionada.setText(imagenLocal.getName());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al leer la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
    
    public File getImagenSeleccionada() {
        return imagenLocal;
    }
}
