package services;

import java.util.List;

import dao.ListaUsuarioEntrenamientoInterface;
import dao.ListaUsuariosInterface;
import modelo.Usuario;

public class UsuarioService {
    private ListaUsuariosInterface listaUsuarios;
    private ListaUsuarioEntrenamientoInterface listaUsuarioEntrenamiento;

    public UsuarioService(ListaUsuariosInterface listaUsuarios, ListaUsuarioEntrenamientoInterface listaUsuarioEntrenamiento) {
        this.listaUsuarios = listaUsuarios;
        this.listaUsuarioEntrenamiento = listaUsuarioEntrenamiento;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios.getListaUsuarios();
    }

    public String addUsuario(Usuario usuario) {
        return listaUsuarios.addUsuario(usuario);
    }
}
