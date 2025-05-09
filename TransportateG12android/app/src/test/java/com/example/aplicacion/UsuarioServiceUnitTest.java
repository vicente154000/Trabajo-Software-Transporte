package com.example.aplicacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

import services.UsuarioService;
import dao.ListaUsuariosInterface;
import dao.ListaUsuarioEntrenamientoInterface;
import modelo.Usuario;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceUnitTest {

    @Mock
    private ListaUsuariosInterface listaUsuarios;

    @Mock
    private ListaUsuarioEntrenamientoInterface listaUsuarioEntrenamiento;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testGetListaUsuarios() {
        //Arrange
        Usuario usuario1 = new Usuario("Jon", "jon@test.com", "contra1", 180, 30, 75);
        Usuario usuario2 = new Usuario("Ane", "ane@test.com", "contra2", 165, 27, 62);
        List<Usuario> usuariosEsperados = Arrays.asList(usuario1, usuario2);

        when(listaUsuarios.getListaUsuarios()).thenReturn(usuariosEsperados);

        //Act
        List<Usuario> usuariosReales = usuarioService.getListaUsuarios();

        //Assert
        Assertions.assertEquals(2, usuariosReales.size());
        Assertions.assertEquals("Juan", usuariosReales.get(0).getNombre());
        Assertions.assertEquals("Maria", usuariosReales.get(1).getNombre());
        verify(listaUsuarios, times(1)).getListaUsuarios();
    }

    @Test
    public void testAddUsuario() {
        //Arrange
        Usuario nuevoUsuario = new Usuario("Jon", "jon@test.com", "contra1", 180, 30, 75);
        String idEsperado = "usuario123";

        when(listaUsuarios.addUsuario(nuevoUsuario)).thenReturn(idEsperado);

        //Act
        String idReal = usuarioService.addUsuario(nuevoUsuario);

        //Assert
        Assertions.assertEquals(idEsperado, idReal);
        verify(listaUsuarios).addUsuario(nuevoUsuario);
    }

    @Test
    public void testDeleteUsuario() {
        //Arrange
        Usuario usuario = new Usuario("Nombre", "usuario@tes.com", "pass", 175, 42, 70);

        //Act
        listaUsuarios.deleteUsuario(usuario);

        //Assert
        verify(listaUsuarios, times(1)).deleteUsuario(usuario);
        verifyNoInteractions(listaUsuarioEntrenamiento);
    }

    @Test
    public void testUpdateUsuario() {
        // Arrange
        Usuario usuario = new Usuario("Nombre", "usuario@test.com", "pass", 175, 30, 72);

        // Act
        listaUsuarios.updateUsuario(usuario);

        // Assert
        verify(listaUsuarios, times(1)).updateUsuario(usuario);
    }
}
