/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.Usuario;

/**
 *
 * @author ruben
 */
public interface ListaUsuariosInterface {
    
    public String addUsuario(Usuario usuario);
    
    public List<Usuario> getListaUsuarios();
    
    public void deleteUsuario(Usuario usuario);
    
    public void updateUsuario(Usuario usuario);
    
}
