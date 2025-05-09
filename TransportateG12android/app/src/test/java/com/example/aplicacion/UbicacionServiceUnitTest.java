package com.example.aplicacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import dao.ListaUbicacionEjercicioInterface;
import dao.ListaUbicacionesInterface;
import services.UbicacionService;

public class UbicacionServiceUnitTest {

    private ListaUbicacionEjercicioInterface listaUbicacionEjercicio;
    private ListaUbicacionesInterface listaUbicaciones;
    private UbicacionService ubicacionService;

    @Test
    public void testGetIdUbicacionPorEjercicio_DevuelveIdCorrecto() {
        //Arrange
        String idEjercicio = "ejercicio123";
        String idEsperado = "ubicacion123";

        when(listaUbicacionEjercicio.getUbicacionPorEjercicio(idEjercicio).getIdUbicacion()).thenReturn(idEsperado);

        //Act
        String idReal = ubicacionService.getIdUbicacionPorEjercicio(idEjercicio);

        //Assert
        Assertions.assertEquals(idEsperado, idReal);
        verify(listaUbicacionEjercicio, times(1)).getUbicacionPorEjercicio(idEjercicio).getIdUbicacion();
    }

    @Test
    public void testGetNombrePorUbicacion_DevuelveNombreCorrecto() {
        //Arrange
        String idUbicacion = "ubicacion123";
        String nombreEsperado = "Aula 001";

        when(listaUbicaciones.getUbicacionPorId(idUbicacion).getNombre()).thenReturn(nombreEsperado);

        //Act
        String nombreReal = ubicacionService.getNombrePorUbicacion(idUbicacion);

        //Assert
        Assertions.assertEquals(nombreEsperado, nombreReal);
        verify(listaUbicaciones, times(1)).getUbicacionPorId(idUbicacion).getNombre();
    }
}
