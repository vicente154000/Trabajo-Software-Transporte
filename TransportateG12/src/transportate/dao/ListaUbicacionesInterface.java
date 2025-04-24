/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.Ubicacion;

/**
 *
 * @author ruben
 */
public interface ListaUbicacionesInterface {
    public String addUbicacion(Ubicacion ubicacion);
    
    public List<Ubicacion> getListaUbicaciones();
    
    public void deleteUbicacion(Ubicacion ubicacion);
    
    public void updateUbicacion(Ubicacion ubicacion);
}
