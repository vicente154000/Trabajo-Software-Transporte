/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.UsuarioEntrenamiento;

/**
 *
 * @author ruben
 */
public interface ListaUsuarioEntrenamientoInterface {
    public String addUsuarioEntrenamiento(UsuarioEntrenamiento usuarioEntrenamiento);
    
    public List<UsuarioEntrenamiento> getListaUsuarioEntrenamiento();
    
    public void deleteUsuarioEntrenamiento(UsuarioEntrenamiento usuario);
    
    public void updateUsuarioEntrenamiento(UsuarioEntrenamiento usuario);
}
