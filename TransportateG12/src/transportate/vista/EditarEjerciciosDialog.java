package transportate.vista;

import transportate.modelo.*;
import transportate.dao.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class EditarEjerciciosDialog extends JDialog {

    private final DefaultListModel<Ejercicio> modeloVinculados;
    private final DefaultListModel<Ejercicio> modeloDisponibles;
    private final JList<Ejercicio> listaVinculados;
    private final JList<Ejercicio> listaDisponibles;
    private final ListaEntrenamientosInterface listaEntrenamientosDAO;
    private final JButton btnAgregar;
    private final JButton btnQuitar;

    public EditarEjerciciosDialog(JFrame parent, Entrenamiento entrenamiento,
                                   List<Ejercicio> todosEjercicios,
                                   ListaEjercicioEntrenamiento daoVinculos,
                                   ListaEntrenamientosInterface listaEntrenamientosDAO) {
        super(parent, "Editar Ejercicios de " + entrenamiento.getId(), true);
        setSize(700, 400);
        setLayout(new BorderLayout());

        modeloVinculados = new DefaultListModel<>();
        modeloDisponibles = new DefaultListModel<>();
        listaVinculados = new JList<>(modeloVinculados);
        listaDisponibles = new JList<>(modeloDisponibles);
        this.listaEntrenamientosDAO = listaEntrenamientosDAO;
        
        Runnable actualizarDatosEntrenamiento = () -> {
            int totalDuracion = 0;
            int totalIntensidad = 0;

            for (int i = 0; i < modeloVinculados.size(); i++) {
                Ejercicio ej = modeloVinculados.getElementAt(i);
                totalDuracion += ej.getDuracion();
                totalIntensidad += ej.getIntensidad();
            }

            entrenamiento.setDuracion(totalDuracion);

            if (modeloVinculados.size() > 0) {
                int promedioIntensidad = totalIntensidad / modeloVinculados.size();
                entrenamiento.setIntensidad(promedioIntensidad);
            } else {
                entrenamiento.setIntensidad(0);
            }

            listaEntrenamientosDAO.updateEntrenamiento(entrenamiento);
        };

        List<EjercicioEntrenamiento> vinculados = daoVinculos.getEjerciciosPorEntrenamiento(entrenamiento.getId());
        List<String> idsVinculados = vinculados.stream()
                .map(EjercicioEntrenamiento::getEjercicioId)
                .collect(Collectors.toList());

        for (Ejercicio e : todosEjercicios) {
            if (idsVinculados.contains(e.getId())) {
                modeloVinculados.addElement(e);
            } else {
                modeloDisponibles.addElement(e);
            }
        }

        JPanel panelListas = new JPanel(new GridLayout(1, 2));
        panelListas.add(new JScrollPane(listaDisponibles));
        panelListas.add(new JScrollPane(listaVinculados));

        btnAgregar = new JButton("Agregar >>");
        btnQuitar = new JButton("<< Quitar");

        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnQuitar);

        JPanel centro = new JPanel(new BorderLayout());
        centro.add(panelListas, BorderLayout.CENTER);
        centro.add(panelBotones, BorderLayout.EAST);

        add(centro, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            Ejercicio seleccionado = listaDisponibles.getSelectedValue();
            if (seleccionado != null) {
                // Añadir relación
                EjercicioEntrenamiento nuevo = new EjercicioEntrenamiento(entrenamiento.getId(), seleccionado.getId());
                String objectId = daoVinculos.addEjercicioEntrenamiento(nuevo);
                if (!objectId.isEmpty()) {
                    nuevo.setObjectId(objectId);
                    modeloVinculados.addElement(seleccionado);
                    modeloDisponibles.removeElement(seleccionado);
                    actualizarDatosEntrenamiento.run();
                }
            }
        });

        btnQuitar.addActionListener(e -> {
            Ejercicio seleccionado = listaVinculados.getSelectedValue();
            if (seleccionado != null) {
                EjercicioEntrenamiento aEliminar = vinculados.stream()
                        .filter(v -> v.getEjercicioId().equals(seleccionado.getId()))
                        .findFirst().orElse(null);

                if (aEliminar != null) {
                    daoVinculos.deleteEjercicioEntrenamiento(aEliminar);
                    modeloDisponibles.addElement(seleccionado);
                    modeloVinculados.removeElement(seleccionado);
                    actualizarDatosEntrenamiento.run();
                }
            }
        });

        setLocationRelativeTo(parent);
        
        
    }
}

