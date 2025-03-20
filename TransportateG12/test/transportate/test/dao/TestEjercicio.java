/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package transportate.test.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transportate.modelo.Ejercicio;
import transportate.modelo.Ubicacion;

/**
 *
 * @author ruben
 */
public class TestEjercicio {
    
    public TestEjercicio() {
    }
    
    @Test
    public void given_new_exercise_inserts_into_list(){
        Ejercicio ejercicio = new Ejercicio("Burpie", "Flexion + salto", 4, 3, new Ubicacion("Aula 002", 100, 100));
    }
}
