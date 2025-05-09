package com.example.aplicacion;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.mockito.Mockito.*;

import dao.ListaEntrenamientosInterface;
import modelo.Entrenamiento;
import services.EntrenamientoService;

public class EntrenamientoServiceUnitTest {

    @Mock
    private ListaEntrenamientosInterface listaEntrenamientos;

    @Mock
    private Random random;

    @InjectMocks
    private EntrenamientoService entrenamientoService;

    @Test
    public void testObtenerEntrenamientoAleatorioPorTipo_DevuelveEntrenamientoAleatorio() {
        //Arrange
        String tipoIntensidad = "media";
        Entrenamiento entrenamiento1 = new Entrenamiento(30, 1);
        Entrenamiento entrenamiento2 = new Entrenamiento(45, 1);
        List<Entrenamiento> entrenamientoList = Arrays.asList(entrenamiento1, entrenamiento2);

        when(listaEntrenamientos.getEntrenamientosPorIntensidad(tipoIntensidad))
                .thenReturn(entrenamientoList);
        when(random.nextInt(entrenamientoList.size())).thenReturn(1);

        //Act
        Entrenamiento entrenamientoReal = entrenamientoService.obtenerEntrenamientoAleatorioPorTipo(tipoIntensidad);

        //Assert
        Assertions.assertEquals(entrenamiento2, entrenamientoReal);
        verify(listaEntrenamientos).getEntrenamientosPorIntensidad(tipoIntensidad);
        verify(random).nextInt(entrenamientoList.size());
    }
}
