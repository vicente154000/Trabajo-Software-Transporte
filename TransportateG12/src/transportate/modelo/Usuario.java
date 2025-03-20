
package transportate.modelo;

import java.util.ArrayList;
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

    // Constructor
    public Usuario(String nombre, String email, String contraseña, int altura, int edad, int peso) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.altura = altura;
        this.edad = edad;
        this.peso = peso;
        this.historial = new ArrayList<>();
    }
    
    //Getter
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getContraseña() { return contraseña; }
    public List<Entrenamiento> getHistorial() { return historial; }
    public int getAltura() { return altura; }
    public int getEdad() { return edad; }
    public int getPeso() { return peso; }

    // Setter
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setAltura(int altura) { this.altura = altura; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setPeso(int peso) { this.peso = peso; }
    public void setEmail(String email) { this.email = email; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    @Override
    public String toString() {
        return "Usuario{" + 
                "nombre=" + nombre + 
                ", email=" + email + 
                ", contrase\u00f1a=" + contraseña + 
                ", historial=" + historial + 
                ", id=" + id + 
                ", altura=" + altura + 
                ", edad=" + edad + 
                ", peso=" + peso + 
                '}';
    }
    
    public void agregarEntrenamientoHistorial(Entrenamiento e) {
        this.historial.add(e);
    }
}