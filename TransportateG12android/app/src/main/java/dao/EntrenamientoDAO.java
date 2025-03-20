package dao;
import modelo.Entrenamiento;
import java.util.List;

public class EntrenamientoDAO {
    public List<Entrenamiento> listaEntrenamientos;

    @Override
    public void addEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.add(entrenamiento);
    }

    @Override
    public List<Entrenamiento> getListaEntrenamientos() {
        return listaEntrenamientos;
    }

    @Override
    public void deleteEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.remove(entrenamiento);
    }

    @Override
    public void updateEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.set(listaEntrenamientos.indexOf(entrenamiento), entrenamiento);
    }

}
