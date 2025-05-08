package services;

import dao.ListaEjercicioEntrenamientoInterface;
import dao.ListaEjerciciosInterface;
import dao.ListaEntrenamientosInterface;
import modelo.Ejercicio;
import modelo.EjercicioEntrenamiento;
import modelo.Entrenamiento;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class EntrenamientoService {
    private ListaEntrenamientosInterface listaEntrenamientos;
    private ListaEjercicioEntrenamientoInterface listaEjercicioEntrenamiento;
    private ListaEjerciciosInterface listaEjercicios;


    public EntrenamientoService(ListaEntrenamientosInterface listaEntrenamientos, ListaEjercicioEntrenamientoInterface listaEjercicioEntrenamiento, ListaEjerciciosInterface listaEjercicios) {
        this.listaEntrenamientos = listaEntrenamientos;
        this.listaEjercicioEntrenamiento = listaEjercicioEntrenamiento;
        this.listaEjercicios = listaEjercicios;
    }

    public Entrenamiento obtenerEntrenamientoAleatorioPorTipo(String tipoIntensidad) {
        List<Entrenamiento> lista = listaEntrenamientos.getEntrenamientosPorIntensidad(tipoIntensidad);

        System.out.println("Lista de entrenamientos: " + lista.toString());

        if (lista.isEmpty()) return null;

        Random random = new Random();

        Entrenamiento entrenamiento = lista.get(random.nextInt(lista.size()));

        System.out.println("Entrenamiento aleatorio: " + entrenamiento.toString());

        return entrenamiento;
    }

    public List<Ejercicio> obtenerEjerciciosPorEntrenamiento(Entrenamiento entrenamiento) {
        List<EjercicioEntrenamiento> ejerciciosEntrenamiento = listaEjercicioEntrenamiento.getEjerciciosPorEntrenamiento(entrenamiento.getId());
        List<Ejercicio> ejercicios = new ArrayList<>();
        for (EjercicioEntrenamiento ejercicioEntrenamiento : ejerciciosEntrenamiento) {
            Ejercicio ejercicio = listaEjercicios.getEjercicioPorId(ejercicioEntrenamiento.getEjercicioId());
            if (ejercicio != null) {
                ejercicios.add(ejercicio);
            }
        }
        return ejercicios;
    }
}
