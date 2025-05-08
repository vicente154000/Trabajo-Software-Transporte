package services;

import dao.ListaUbicacionEjercicioInterface;
import dao.ListaUbicacionesInterface;

public class UbicacionService {
    private ListaUbicacionesInterface listaUbicaciones;
    private ListaUbicacionEjercicioInterface listaUbicacionEjercicio;

    public UbicacionService(ListaUbicacionesInterface listaUbicaciones, ListaUbicacionEjercicioInterface listaUbicacionEjercicio) {
        this.listaUbicaciones = listaUbicaciones;
        this.listaUbicacionEjercicio = listaUbicacionEjercicio;
    }

    public String getIdUbicacionPorEjercicio(String ejercicioId) {
        return listaUbicacionEjercicio.getUbicacionPorEjercicio(ejercicioId).getIdUbicacion();
    }

    public double getLatitudPorUbicacion(String ubicacionId) {
        return listaUbicaciones.getUbicacionPorId(ubicacionId).getLatitud();
    }

    public double getLongitudPorUbicacion(String ubicacionId) {
        return listaUbicaciones.getUbicacionPorId(ubicacionId).getLongitud();
    }

    public String getNombrePorUbicacion(String ubicacionId) {
        return listaUbicaciones.getUbicacionPorId(ubicacionId).getNombre();
    }
}
