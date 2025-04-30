package negocio;

import dao.ListaEntrenamientos;
import modelo.Entrenamiento;
import java.util.List;
import java.util.Random;

public class EntrenamientoNegocio {
    private ListaEntrenamientos dao;

    public EntrenamientoNegocio(ListaEntrenamientos dao) {
        this.dao = dao;
    }

    public Entrenamiento obtenerEntrenamientoAleatorioPorTipo(String tipoIntensidad) {
        List<Entrenamiento> lista = dao.getEntrenamientosPorIntensidad(tipoIntensidad);

        System.out.println("Lista de entrenamientos: " + lista.toString());

        if (lista.isEmpty()) return null;

        Random random = new Random();

        Entrenamiento entrenamiento = lista.get(random.nextInt(lista.size()));

        System.out.println("Entrenamiento aleatorio: " + entrenamiento.toString());

        return entrenamiento;
    }
}
