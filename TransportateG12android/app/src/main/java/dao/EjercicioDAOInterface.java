package dao;
import modelo.Ejercicio;
import java.util.List;

public class EjercicioDAOInterface {
    public void addEjercicio(Ejercicio ejercicio);

    public void deleteEjercicio(Ejercicio ejercicio);

    public List<Ejercicio> getListaEjercicios();

    public void updateEjercicio(Ejercicio ejercicio);
}