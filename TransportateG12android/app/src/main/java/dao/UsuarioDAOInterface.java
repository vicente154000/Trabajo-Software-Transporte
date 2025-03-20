package dao;
import modelo.Usuario;
import java.util.List;

public class UsuarioDAInterface {
    public void addEjercicio(Ejercicio ejercicio);

    public void deleteEjercicio(Ejercicio ejercicio);

    public List<Ejercicio> getListaEjercicios();

    public void updateEjercicio(Ejercicio ejercicio);
}