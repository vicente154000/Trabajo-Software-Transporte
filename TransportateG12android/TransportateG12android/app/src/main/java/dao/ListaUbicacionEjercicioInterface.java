/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.UbicacionEjercicio;

/**
 *
 * @author ruben
 */
public interface ListaUbicacionEjercicioInterface {
    public String addUbicacionEjercicio(UbicacionEjercicio ubicacionEjercicio);
    
    public List<UbicacionEjercicio> getListaUbicacionesEjercicios();
    
    public void deleteUbicacionEjercicio(UbicacionEjercicio ubicacionEjercicio);
    
    public void updateUbicacionEjercicio(UbicacionEjercicio ubicacionEjercicio);

    public UbicacionEjercicio getUbicacionPorEjercicio(String ejercicioId);
}
