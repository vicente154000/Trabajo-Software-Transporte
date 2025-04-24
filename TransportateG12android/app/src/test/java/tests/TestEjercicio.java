/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tests;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

import dao.ListaEjercicios;
import modelo.Ejercicio;
import modelo.Ubicacion;

/**
 *
 * @author ruben
 */
public class TestEjercicio {
    
    public TestEjercicio() {
    }
    @Test
    public void given_new_exercise_inserts_into_list(){
        ListaEjercicios listaEjercicios = new ListaEjercicios();
        Ejercicio ejercicio = new Ejercicio(1, "Burpie", "Flexion + salto", 4, 3, new Ubicacion("Aula 002", 100, 100));
        listaEjercicios.addEjercicio(ejercicio);
        List<Ejercicio> ejercicios = listaEjercicios.getListaEjercicios();
        assertEquals(ejercicio,ejercicios.get(0));
    }
    
    @Test
    public void given_existing_exercise_deletes_from_list() {
        ListaEjercicios listaEjercicios = new ListaEjercicios();
        Ejercicio ejercicio = new Ejercicio(1, "Burpie", "Flexión + salto", 4, 3, new Ubicacion("Aula 002", 100, 100));
        listaEjercicios.addEjercicio(ejercicio);
        assertTrue(listaEjercicios.getListaEjercicios().contains(ejercicio));
        listaEjercicios.deleteEjercicio(ejercicio);
        assertFalse(listaEjercicios.getListaEjercicios().contains(ejercicio));
        assertEquals(0, listaEjercicios.getListaEjercicios().size());
    }
}
