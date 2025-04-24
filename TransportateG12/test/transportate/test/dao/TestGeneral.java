package transportate.test.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transportate.modelo.*;
import transportate.dao.*;
import java.util.*;

/**
 *
 * @author ruben
 */
public class TestGeneral {
    
    private Ubicacion aula221;
    private Ubicacion zonaSofas;
    private Ejercicio ejercicio1;
    private Ejercicio ejercicio2;
    private Usuario usuario1;
    private Usuario usuario2;
    private ListaEjercicios listaEjercicios;
    private ListaUsuarios listaUsuarios;
    private ListaEntrenamientos listaEntrenamientos;

    @BeforeEach
    void setUp() {
        aula221 = new Ubicacion("Aula 221", 40.7128, -74.0060);
        zonaSofas = new Ubicacion("Zona de sofás", 40.7135, -74.0055);

        ejercicio1 = new Ejercicio(1, "Flexiones", "Ejercicio de fuerza", 10, 5, aula221);
        ejercicio2 = new Ejercicio(2, "Sentadillas", "Ejercicio de piernas", 15, 7, zonaSofas);

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
        double distancia = aula221.calcularDistancia(zonaSofas);
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

