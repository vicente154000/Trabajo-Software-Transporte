/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportate.dao;

import java.util.List;
import transportate.modelo.Ejercicio;

/**
 *
 * @author ruben
 */
public class ListaEjercicios implements ListaEjerciciosInterface{
    private List<Ejercicio> listaEjercicios;

    @Override
    public void addEjercicio(Ejercicio ejercicio) {
        listaEjercicios.add(ejercicio);
    }

    @Override
    public void deleteEjercicio(Ejercicio ejercicio) {
        listaEjercicios.remove(ejercicio);
    }

    @Override
    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    @Override
    public void updateEjercicio(Ejercicio ejercicio) {
        listaEjercicios.set(listaEjercicios.indexOf(ejercicio), ejercicio);
    }
    
}
