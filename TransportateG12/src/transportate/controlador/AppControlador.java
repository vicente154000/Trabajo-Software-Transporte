package transportate.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import transportate.dao.ListaEjercicioEntrenamiento;
import transportate.dao.ListaEjerciciosInterface;
import transportate.dao.ListaEntrenamientosInterface;
import transportate.dao.ListaUbicacionEjercicioInterface;
import transportate.dao.ListaUbicaciones;
import transportate.dao.ListaUbicacionesInterface;
import transportate.modelo.Ejercicio;
import transportate.modelo.Entrenamiento;
import transportate.modelo.Ubicacion;
import transportate.modelo.UbicacionEjercicio;
import transportate.vista.EditarEjerciciosDialog;
import transportate.vista.EjerciciosVista;
import transportate.vista.EntrenamientosVista;
import transportate.vista.NuevaUbicacionDialog;
import transportate.vista.NuevoEjercicioDialog;
import transportate.vista.UbicacionesVista;
import transportate.vista.VistaPrincipal;

public class AppControlador {

    private ListaEjerciciosInterface listaEjerciciosDAO;
    private ListaEntrenamientosInterface listaEntrenamientosDAO;
    private ListaEjercicioEntrenamiento listaEjercicioEntrenamientoDAO;
    private ListaUbicacionesInterface listaUbicacionesDAO;
    private ListaUbicacionEjercicioInterface listaUbicacionEjercicioDAO;

    private VistaPrincipal vistaPrincipal;
    private EjerciciosVista ejerciciosVista;
    private EntrenamientosVista entrenamientosVista;
    private UbicacionesVista ubicacionesVista;

    public AppControlador(
        ListaEjerciciosInterface listaEjerciciosDAO, 
        ListaEntrenamientosInterface listaEntrenamientosDAO,
        VistaPrincipal vistaPrincipal,
        EjerciciosVista ejerciciosVista,
        EntrenamientosVista entrenamientosVista,
        ListaEjercicioEntrenamiento listaEjercicioEntrenamientoDAO,
        ListaUbicaciones listaUbicacionesDAO,
        UbicacionesVista ubicacionesVista,
        ListaUbicacionEjercicioInterface listaUbicacionEjercicioDAO 
    ) {
        this.listaEjerciciosDAO = listaEjerciciosDAO;
        this.listaEntrenamientosDAO = listaEntrenamientosDAO;
        this.listaEjercicioEntrenamientoDAO = listaEjercicioEntrenamientoDAO;
        this.listaUbicacionesDAO = listaUbicacionesDAO;
        this.listaUbicacionEjercicioDAO = listaUbicacionEjercicioDAO;

        this.vistaPrincipal = vistaPrincipal;
        this.ejerciciosVista = ejerciciosVista;
        this.entrenamientosVista = entrenamientosVista;
        this.ubicacionesVista = ubicacionesVista;

        iniciar();
    }

    private void iniciar() {
        // Mostrar datos iniciales
        cargarEjercicios();
        cargarEntrenamientos();
        cargarUbicaciones();

        // Listeners para Ejercicios
        ejerciciosVista.getBtnAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ubicacion> ubicaciones = listaUbicacionesDAO.getListaUbicaciones();
                NuevoEjercicioDialog dialog = new NuevoEjercicioDialog(vistaPrincipal, ubicaciones);
                dialog.setVisible(true);

                Ejercicio nuevo = dialog.getEjercicioCreado();
                Ubicacion ubicacionSeleccionada = dialog.getUbicacionSeleccionada();

                if (nuevo != null && ubicacionSeleccionada != null) {
                    String ejercicioId = listaEjerciciosDAO.addEjercicio(nuevo);
                    if (!ejercicioId.isEmpty()) {
                        UbicacionEjercicio relacion = new UbicacionEjercicio(ejercicioId, ubicacionSeleccionada.getId());
                        listaUbicacionEjercicioDAO.addUbicacionEjercicio(relacion);
                    }
                    cargarEjercicios();
                }
            }
        });

        ejerciciosVista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ejercicio seleccionado = ejerciciosVista.getSeleccionado();
                if (seleccionado != null) {
                    listaEjerciciosDAO.deleteEjercicio(seleccionado);
                    cargarEjercicios();
                }
            }
        });

        // Listeners para Entrenamientos
        entrenamientosVista.getBtnAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entrenamiento nuevo = new Entrenamiento(0, 0);
                listaEntrenamientosDAO.addEntrenamiento(nuevo);
                cargarEntrenamientos();
            }
        });
        
        entrenamientosVista.getBtnEditarEjercicios().addActionListener(e -> {
            Entrenamiento seleccionado = entrenamientosVista.getSeleccionado();
            if (seleccionado != null) {
                List<Ejercicio> todos = listaEjerciciosDAO.getListaEjercicios();
                EditarEjerciciosDialog dialog = new EditarEjerciciosDialog(vistaPrincipal, seleccionado, todos, listaEjercicioEntrenamientoDAO, listaEntrenamientosDAO);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(vistaPrincipal, "Selecciona un entrenamiento primero.");
            }
        });

        entrenamientosVista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entrenamiento seleccionado = entrenamientosVista.getSeleccionado();
                if (seleccionado != null) {
                    listaEntrenamientosDAO.deleteEntrenamiento(seleccionado);
                    cargarEntrenamientos();
                }
            }
        });
        
        // Listeners para Ubicaciones
        ubicacionesVista.getBtnAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuevaUbicacionDialog dialog = new NuevaUbicacionDialog(vistaPrincipal);
                dialog.setVisible(true);

                Ubicacion nuevo = dialog.getUbicacionCreada();

                if (nuevo != null) {
                    String ubicacionId = listaUbicacionesDAO.addUbicacion(nuevo);
                    cargarUbicaciones();
                }
            }
        });
        
        ubicacionesVista.getBtnEliminar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Ubicacion seleccionada = ubicacionesVista.getSeleccionado();
                if (seleccionada != null) {
                    listaUbicacionesDAO.deleteUbicacion(seleccionada);
                    cargarUbicaciones();
                }
            }
        });
    }

    private void cargarEjercicios() {
        List<Ejercicio> ejercicios = listaEjerciciosDAO.getListaEjercicios();
        ejerciciosVista.mostrarEjercicios(ejercicios);
    }

    private void cargarEntrenamientos() {
        List<Entrenamiento> entrenamientos = listaEntrenamientosDAO.getListaEntrenamientos();
        entrenamientosVista.mostrarEntrenamientos(entrenamientos);
    }
    
    private void cargarUbicaciones() {
        List<Ubicacion> ubicaciones = listaUbicacionesDAO.getListaUbicaciones();
        ubicacionesVista.mostrarUbicaciones(ubicaciones);
    }
}
