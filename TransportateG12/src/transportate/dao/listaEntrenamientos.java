package transportate.dao;

import java.util.ArrayList;
import java.util.List;
import transportate.modelo.Entrenamiento;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ruben
 */
public class ListaEntrenamientos implements ListaEntrenamientosInterface{

    public List<Entrenamiento> listaEntrenamientos;

    public ListaEntrenamientos() {
        listaEntrenamientos = new ArrayList<>();
    }
    
    @Override
    public void addEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.add(entrenamiento);
    }

    @Override
    public List<Entrenamiento> getListaEntrenamientos() {
        return listaEntrenamientos;
    }

    @Override
    public void deleteEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.remove(entrenamiento);
    }

    @Override
    public void updateEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.set(listaEntrenamientos.indexOf(entrenamiento), entrenamiento);
    }
    
}
