/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.Entrenamiento;

/**
 *
 * @author ruben
 */
public interface ListaEntrenamientosInterface {
    
    public String addEntrenamiento(Entrenamiento entrenamiento);
    
    public List<Entrenamiento> getListaEntrenamientos();
    
    public void deleteEntrenamiento(Entrenamiento entrenamiento);
    
    public void updateEntrenamiento(Entrenamiento entrenamiento);

    public List<Entrenamiento> getEntrenamientosPorIntensidad(String tipoIntensidad);
    
}
