package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dao.ListaEjercicios;
import dao.ListaEntrenamientos;
import dao.ListaUsuarios;
import modelo.*;
import java.util.*;

/**
 *
 * @author ruben
 */
public class TestGeneral {
    
    private Ubicacion aula004;
    private Ubicacion aula321;
    private Ejercicio ejercicio1;
    private Ejercicio ejercicio2;
    private Usuario usuario1;
    private Usuario usuario2;
    private ListaEjercicios listaEjercicios;
    private ListaUsuarios listaUsuarios;
    private ListaEntrenamientos listaEntrenamientos;

    @BeforeEach
    void setUp() {
        aula004 = new Ubicacion("Aula 004", 18.7264, 90.72647);
        aula321 = new Ubicacion("Aula 321", 84.6378, -14.0823);

        ejercicio1 = new Ejercicio(1, "Flexiones", "Ejercicio de fuerza", 10, 5, aula004);
        ejercicio2 = new Ejercicio(2, "Sentadillas", "Ejercicio de piernas", 15, 7, aula321);

        listaEjercicios = new ListaEjercicios();
        listaEjercicios.addEjercicio(ejercicio1);
        listaEjercicios.addEjercicio(ejercicio2);

        usuario1 = new Usuario("Juan", "juan@example.com", "password123", 180, 25, 75);
        usuario2 = new Usuario("Maria", "maria@example.com", "contrasenia123", 165, 20, 53);

        listaUsuarios = new ListaUsuarios();
        listaUsuarios.addUsuario(usuario1);
        listaUsuarios.addUsuario(usuario2);

        listaEntrenamientos = new ListaEntrenamientos();
    }
    
    @Test
    public void testCalculoDistanciaUbicaciones() {
        double distancia = aula004.calcularDistancia(aula321);
        assertTrue(distancia > 0);
    }

    @Test
    public void testAgregarEjercicio() {
        assertEquals(2, listaEjercicios.getListaEjercicios().size());
    }

    @Test
    public void testActualizarEjercicio() {
        ejercicio1.setDuracion(20);
        listaEjercicios.updateEjercicio(ejercicio1);
        assertEquals(20, listaEjercicios.getListaEjercicios().get(0).getDuracion());
    }

    @Test
    public void testEliminarEjercicio() {
        listaEjercicios.deleteEjercicio(ejercicio2);
        assertEquals(1, listaEjercicios.getListaEjercicios().size());
    }

    @Test
    public void testAgregarUsuario() {
        assertEquals(2, listaUsuarios.getListaUsuarios().size());
    }

    @Test
    public void testAgregarEntrenamiento() {
        List<Ejercicio> ejerciciosEntrenamiento = Arrays.asList(ejercicio1, ejercicio2);
        Entrenamiento entrenamiento = new Entrenamiento(1, usuario1.getId(), 30, 200, new Date(), ejerciciosEntrenamiento);
        listaEntrenamientos.addEntrenamiento(entrenamiento);
        assertEquals(1, listaEntrenamientos.getListaEntrenamientos().size());
    }
}

