/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tests;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dao.ListaUsuarios;
import modelo.Usuario;

/**
 *
 * @author ruben
 */
public class TestUsuario {
    
    public TestUsuario() {
        
    }
    
    @Test
    public void given_new_user_inserts_list(){
        Usuario usuario = new Usuario("Pepito", "123@e.com", "1234", 180, 40, 86);
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        
        listaUsuarios.addUsuario(usuario);
        
        assertTrue(listaUsuarios.getListaUsuarios().contains(usuario));
    }
    
    @Test
    public void given_list_deletes_user(){
        Usuario usuario = new Usuario("Pepito", "123@e.com", "1234", 180, 40, 86);
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        
        listaUsuarios.addUsuario(usuario);
        listaUsuarios.deleteUsuario(usuario);
        
        assertFalse(listaUsuarios.getListaUsuarios().contains(usuario));
    }
    
}
