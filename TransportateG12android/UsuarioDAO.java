package dao;
import modelo.Usuario;
import java.util.List;

public class UsuarioDAO {
    private List<Ejercicio> listaEjercicios;

    @Override
    public void addEjercicio(Ejercicio ejercicio) {
        listaEjercicios.add(ejercicio);
    }

    @Override
    public void deleteEjercicio(Ejercicio ejercicio) {
        listaEjercicios.remove(ejercicio);
    }

    @Override
    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    @Override
    public void updateEjercicio(Ejercicio ejercicio) {
        listaEjercicios.set(listaEjercicios.indexOf(ejercicio), ejercicio);
    }

}
