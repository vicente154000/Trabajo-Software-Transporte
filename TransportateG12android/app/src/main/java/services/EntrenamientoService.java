package services;

import dao.ListaEjercicioEntrenamientoInterface;
import dao.ListaEntrenamientosInterface;
import modelo.Ejercicio;
import modelo.Entrenamiento;
import java.util.List;
import java.util.Random;

public class EntrenamientoService {
    private ListaEntrenamientosInterface listaEntrenamientos;
    private ListaEjercicioEntrenamientoInterface listaEjercicioEntrenamiento;


    public EntrenamientoService(ListaEntrenamientosInterface listaEntrenamientos, ListaEjercicioEntrenamientoInterface listaEjercicioEntrenamiento) {
        this.listaEntrenamientos = listaEntrenamientos;
        this.listaEjercicioEntrenamiento = listaEjercicioEntrenamiento;
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
        return listaEjercicioEntrenamiento.getEjerciciosDeUnEntrenamiento(entrenamiento.getId());
    }
}
