/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Ejercicio;

/**
 *
 * @author ruben
 */
public interface ListaEjerciciosInterface {
    
    public void addEjercicio(Ejercicio ejercicio);
    
    public void deleteEjercicio(Ejercicio ejercicio);
    
    public List<Ejercicio> getListaEjercicios();
    
    public void updateEjercicio(Ejercicio ejercicio);
}
