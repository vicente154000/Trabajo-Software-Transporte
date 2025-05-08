
import transportate.controlador.AppControlador;
import transportate.dao.ListaEjercicioEntrenamiento;
import transportate.dao.ListaEjercicios;
import transportate.dao.ListaEntrenamientos;
import transportate.dao.ListaImagenEjercicio;
import transportate.dao.ListaUbicacionEjercicio;
import transportate.dao.ListaUbicaciones;
import transportate.vista.EjerciciosVista;
import transportate.vista.EntrenamientosVista;
import transportate.vista.UbicacionesVista;
import transportate.vista.VistaPrincipal;

public class Main {
    public static void main(String[] args) {
        // Tus claves de Back4App
        //String appID = "PZhlOWsdhQur0CyiDdUTnjofkAnKCFu6tJyymCpM";
        //String restKey = "plmowUHRNjWQ5tCW85rr26EWeu3RW44c6lAJAgGe";
        
        String appID = "zyil9FTR6dHvZuZ57QGSKOFbLIGaWv9FqFMHPuWE";
        String restKey = "UgCjCbEwJBQxcvUn4z5Plx6iDdeLHbHVsI7EOlDc";

        var ejerciciosDAO = new ListaEjercicios(appID, restKey);
        var entrenamientosDAO = new ListaEntrenamientos(appID, restKey);
        var ejerEntrenamientoDAO = new ListaEjercicioEntrenamiento(appID, restKey);
        var ubicacionesDAO = new ListaUbicaciones(appID, restKey);
        var ubicacionEjercicioDAO = new ListaUbicacionEjercicio(appID, restKey);
        var imagenEjercicioDAO = new ListaImagenEjercicio(appID, restKey);

        var ejerciciosVista = new EjerciciosVista();
        var entrenamientosVista = new EntrenamientosVista();
        var ubicacionesVista = new UbicacionesVista();
        var vistaPrincipal = new VistaPrincipal(ejerciciosVista, entrenamientosVista, ubicacionesVista);

        new AppControlador(
            ejerciciosDAO,
            entrenamientosDAO,
            vistaPrincipal,
            ejerciciosVista,
            entrenamientosVista,
            ejerEntrenamientoDAO,
            ubicacionesDAO,
            ubicacionesVista,
            ubicacionEjercicioDAO,
            imagenEjercicioDAO
        );

        vistaPrincipal.setVisible(true);
    }
}