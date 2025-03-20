
package transportate.modelo;

import java.util.List;

public class Usuario {
    private String nombre;
    private String email;
    private String contraseña;
    private List<Entrenamiento> historial;
    private int id;
    private int altura;
    private int edad;
    private int peso;
    
    public Usuario(String nombre, String email, String contraseña, List<Entrenamiento> historial, int altura, int peso, int edad){
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.historial = historial;
        this.altura = altura;
        this.edad = edad;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public List<Entrenamiento> getHistorial() {
        return historial;
    }

    public int getId() {
        return id;
    }

    public int getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setHistorial(List<Entrenamiento> historial) {
        this.historial = historial;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
}
