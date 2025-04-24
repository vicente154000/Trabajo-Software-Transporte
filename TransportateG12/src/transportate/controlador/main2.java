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

public class main2 {
    
    private final static String APP_ID = "PZhlOWsdhQur0CyiDdUTnjofkAnKCFu6tJyymCpM";
    private final static String REST_API_KEY = "plmowUHRNjWQ5tCW85rr26EWeu3RW44c6lAJAgGe";

    public static void main(String[] args) {
        Ubicacion aula004 = new Ubicacion("Aula 004", 18.7264, 90.72647);
        Ubicacion aula321 = new Ubicacion("Aula 321", 84.6378, -14.0823);

        mostrarDistancia(aula004, aula321);

        ListaEjercicios listaEjercicios = inicializarEjercicios(aula004, aula321);
        mostrarEjercicios(listaEjercicios);

        ListaUsuarios listaUsuarios = inicializarUsuarios();
        mostrarUsuarios(listaUsuarios);

        ListaEntrenamientos listaEntrenamientos = inicializarEntrenamientos(listaUsuarios, listaEjercicios);
        mostrarEntrenamientos(listaEntrenamientos);

        actualizarEjercicio(listaEjercicios);
        mostrarEjercicios(listaEjercicios);

        eliminarEjercicio(listaEjercicios);
        mostrarEjercicios(listaEjercicios);
    }

    private static void mostrarDistancia(Ubicacion u1, Ubicacion u2) {
        System.out.println("Distancia entre " + u1.getNombre() + " y " + u2.getNombre() + ": " +
                u1.calcularDistancia(u2) + " km");
    }

    private static ListaEjercicios inicializarEjercicios(Ubicacion aula004, Ubicacion aula321) {
        ListaEjercicios lista = new ListaEjercicios(APP_ID, REST_API_KEY);
        String id = lista.addEjercicio(new Ejercicio("Abdominales", "Ejercicio para fortalecer el abdomen", 10, 5, aula004.getNombre()));
        System.out.println("ID NUEVO: " + id);
        lista.addEjercicio(new Ejercicio("Pull-ups", "Ejercicio para espalda y brazos", 15, 7, aula321.getNombre()));
        return lista;
    }

    private static void mostrarEjercicios(ListaEjercicios lista) {
        System.out.println("Lista de ejercicios:");
        lista.getListaEjercicios().forEach(System.out::println);
    }

    private static ListaUsuarios inicializarUsuarios() {
        ListaUsuarios lista = new ListaUsuarios(APP_ID, REST_API_KEY);
        lista.addUsuario(new Usuario("Carlos", "carlos@example.com", "miContraseña123", 175, 30, 80));
        lista.addUsuario(new Usuario("Sofia", "sofia@example.com", "contraseñaSegura456", 160, 28, 65));
        System.out.println("Usuarios añadidos");
        return lista;
    }

    private static void mostrarUsuarios(ListaUsuarios lista) {
        System.out.println("Lista de usuarios:");
        //lista.getListaUsuarios().forEach(System.out::println);
    }

    private static ListaEntrenamientos inicializarEntrenamientos(ListaUsuarios listaUsuarios, ListaEjercicios listaEjercicios) {
        ListaEntrenamientos lista = new ListaEntrenamientos(APP_ID, REST_API_KEY);
        Entrenamiento entrenamiento = new Entrenamiento(30, 2);
        lista.addEntrenamiento(entrenamiento);
        return lista;
    }

    private static void mostrarEntrenamientos(ListaEntrenamientos lista) {
        System.out.println("Lista de entrenamientos:");
        lista.getListaEntrenamientos().forEach(System.out::println);
    }

    private static void actualizarEjercicio(ListaEjercicios lista) {
        Ejercicio ejercicio = lista.getListaEjercicios().get(0);
        ejercicio.setDuracion(20);
        lista.updateEjercicio(ejercicio);
    }

    private static void eliminarEjercicio(ListaEjercicios lista) {
        lista.deleteEjercicio(lista.getListaEjercicios().get(1));
    }
}
