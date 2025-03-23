package transportate.controlador;

import transportate.modelo.Ubicacion;
import transportate.modelo.Ejercicio;
import transportate.modelo.Entrenamiento;
import transportate.modelo.Usuario;
import transportate.dao.ListaEjercicios;
import transportate.dao.ListaEntrenamientos;
import transportate.dao.ListaUsuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main {
    
    public static void main(String[] args) {
        // Crear ubicaciones
        Ubicacion aula221 = new Ubicacion("Aula 221", 40.7128, -74.0060);
        Ubicacion zonaSofas = new Ubicacion("Zona de sofás", 40.7135, -74.0055);

        // Calcular y mostrar la distancia entre las ubicaciones
        System.out.println("Distancia entre " + aula221.getNombre() + " y " + zonaSofas.getNombre() + ": " +
                aula221.calcularDistancia(zonaSofas) + " km");

        // Crear ejercicios
        Ejercicio ejercicio1 = new Ejercicio(1, "Flexiones", "Ejercicio de fuerza", 10, 5, aula221);
        Ejercicio ejercicio2 = new Ejercicio(2, "Sentadillas", "Ejercicio de piernas", 15, 7, zonaSofas);

        // Crear lista de ejercicios y agregar ejercicios
        ListaEjercicios listaEjercicios = new ListaEjercicios();
        listaEjercicios.addEjercicio(ejercicio1);
        listaEjercicios.addEjercicio(ejercicio2);

        // Mostrar lista de ejercicios
        System.out.println("Lista de ejercicios:");
        for (Ejercicio ejercicio : listaEjercicios.getListaEjercicios()) {
            System.out.println(ejercicio);
        }

        // Crear usuario
        Usuario usuario1 = new Usuario("Juan", "juan@example.com", "password123", 180, 25, 75);
        Usuario usuario2 = new Usuario("Maria", "maria@example.com", "contrasenia123", 165, 20, 53);

        // Crear lista de usuarios y agregar usuario
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        listaUsuarios.addUsuario(usuario1);
        listaUsuarios.addUsuario(usuario2);
        
        // Mostrar lista de usuarios
        System.out.println("Lista de usuarios:");
        for (Usuario u : listaUsuarios.getListaUsuarios()) {
            System.out.println(u);
        }

        // Crear entrenamiento
        List<Ejercicio> ejerciciosEntrenamiento = new ArrayList<>();
        ejerciciosEntrenamiento.add(ejercicio1);
        ejerciciosEntrenamiento.add(ejercicio2);
        Entrenamiento entrenamientoUsuario1 = new Entrenamiento(1, usuario1.getId(), 30, 200, new Date(), ejerciciosEntrenamiento);
        usuario1.agregarEntrenamientoHistorial(entrenamientoUsuario1);
        
        // Crear lista de entrenamientos y agregar entrenamiento
        ListaEntrenamientos listaEntrenamientos = new ListaEntrenamientos();
        listaEntrenamientos.addEntrenamiento(entrenamientoUsuario1);

        // Mostrar lista de entrenamientos
        System.out.println("Lista de entrenamientos:");
        for (Entrenamiento e : listaEntrenamientos.getListaEntrenamientos()) {
            System.out.println(e);
        }

        // Actualizar un ejercicio
        ejercicio1.setDuracion(20);
        listaEjercicios.updateEjercicio(ejercicio1);

        // Mostrar lista de ejercicios actualizada
        System.out.println("Lista de ejercicios actualizada:");
        for (Ejercicio ejercicio : listaEjercicios.getListaEjercicios()) {
            System.out.println(ejercicio);
        }

        // Eliminar un ejercicio
        listaEjercicios.deleteEjercicio(ejercicio2);

        // Mostrar lista de ejercicios después de eliminar
        System.out.println("Lista de ejercicios después de eliminar:");
        for (Ejercicio ejercicio : listaEjercicios.getListaEjercicios()) {
            System.out.println(ejercicio);
        }
    }
}