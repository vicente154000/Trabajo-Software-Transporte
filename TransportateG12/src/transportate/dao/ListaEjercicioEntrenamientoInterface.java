/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.EjercicioEntrenamiento;

/**
 *
 * @author ruben
 */
public interface ListaEjercicioEntrenamientoInterface {
    public String addEjercicioEntrenamiento(EjercicioEntrenamiento ejercicioEntrenamiento);
    
    public void deleteEjercicioEntrenamiento(EjercicioEntrenamiento ejercicioEntrenamiento);
    
    public void updatejercicioEntrenamiento(EjercicioEntrenamiento ejercicioEntrenamiento);
    
    public List<EjercicioEntrenamiento> getAllEjercicioEntrenamiento();
}
