package dao;
import modelo.Entrenamiento;
import java.util.List;

public class EntrenamientoDAOInterface {
    public void addEntrenamiento(Entrenamiento entrenamiento);

    public List<Entrenamiento> getListaEntrenamientos();

    public void deleteEntrenamiento(Entrenamiento entrenamiento);

    public void updateEntrenamiento(Entrenamiento entrenamiento);
}