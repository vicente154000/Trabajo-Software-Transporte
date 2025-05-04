package com.example.aplicacion;

import android.app.Application;
import android.os.StrictMode;

import dao.ListaEjercicioEntrenamiento;
import dao.ListaEjercicioEntrenamientoInterface;
import dao.ListaEjercicios;
import dao.ListaEjerciciosInterface;
import dao.ListaEntrenamientos;
import dao.ListaEntrenamientosInterface;
import dao.ListaUbicacionEjercicio;
import dao.ListaUbicacionEjercicioInterface;
import dao.ListaUbicaciones;
import dao.ListaUbicacionesInterface;
import dao.ListaUsuarioEntrenamiento;
import dao.ListaUsuarioEntrenamientoInterface;
import dao.ListaUsuarios;
import dao.ListaUsuariosInterface;
import services.EntrenamientoService;
import services.UbicacionService;
import services.UsuarioService;

public class LayerApplication extends Application {

    private static final String APP_ID = "PZhlOWsdhQur0CyiDdUTnjofkAnKCFu6tJyymCpM";
    private static final String CLIENT_KEY = "plmowUHRNjWQ5tCW85rr26EWeu3RW44c6lAJAgGe";
    private ListaEntrenamientosInterface listaEntrenamientos;
    private ListaEjercicioEntrenamientoInterface listaEjercicioEntrenamiento;
    private ListaEjerciciosInterface listaEjercicios;
    private ListaUbicacionEjercicioInterface listaUbicacionEjercicio;
    private ListaUsuarioEntrenamientoInterface listaUsuarioEntrenamiento;
    private ListaUsuariosInterface listaUsuarios;
    private ListaUbicacionesInterface listaUbicaciones;
    @Override
    public void onCreate() {
        super.onCreate();

        this.listaEntrenamientos = new ListaEntrenamientos(APP_ID, CLIENT_KEY);
        this.listaEjercicioEntrenamiento = new ListaEjercicioEntrenamiento(APP_ID, CLIENT_KEY);
        this.listaEjercicios = new ListaEjercicios(APP_ID, CLIENT_KEY);
        this.listaUbicacionEjercicio = new ListaUbicacionEjercicio(APP_ID, CLIENT_KEY);
        this.listaUsuarioEntrenamiento = new ListaUsuarioEntrenamiento(APP_ID, CLIENT_KEY);
        this.listaUsuarios = new ListaUsuarios(APP_ID, CLIENT_KEY);
        this.listaUbicaciones = new ListaUbicaciones(APP_ID, CLIENT_KEY);
    }

    public EntrenamientoService getEntrenamientosService() {
        return new EntrenamientoService(listaEntrenamientos, listaEjercicioEntrenamiento, listaEjercicios);
    }

    public UbicacionService getUbicacionService() {
        return new UbicacionService(listaUbicaciones, listaUbicacionEjercicio);
    }

    public UsuarioService getUsuarioService() {
        return new UsuarioService(listaUsuarios, listaUsuarioEntrenamiento);
    }
}
