/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Ubicacion;

/**
 *
 * @author ruben
 */
public interface ListaUbicacionesInterface {
    public String addUbicacion(Ubicacion ubicacion);
    
    public List<Ubicacion> getListaUbicaciones();
    
    public void deleteUbicacion(Ubicacion ubicacion);
    
    public void updateUbicacion(Ubicacion ubicacion);

    public Ubicacion getUbicacionPorId(String idUbicacion);
}
