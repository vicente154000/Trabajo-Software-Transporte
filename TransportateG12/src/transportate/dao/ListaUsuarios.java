/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.Usuario;

/**
 *
 * @author ruben
 */
public class ListaUsuarios implements ListaUsuariosInterface{

    public List<Usuario> listaUsuarios;
    
    @Override
    public void addUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        listaUsuarios.remove(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        listaUsuarios.set(listaUsuarios.indexOf(usuario), usuario);
    }
    
}
