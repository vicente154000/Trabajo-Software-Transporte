/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author ruben
 */
public interface ListaUsuariosInterface {
    
    public void addUsuario(Usuario usuario);
    
    public List<Usuario> getListaUsuarios();
    
    public void deleteUsuario(Usuario usuario);
    
    public void updateUsuario(Usuario usuario);
    
}
