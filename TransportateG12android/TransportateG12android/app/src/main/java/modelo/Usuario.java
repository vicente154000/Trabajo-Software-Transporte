
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombre;
    private String email;
    private String password;
    private String objectId;
    private int altura;
    private int edad;
    private int peso;

    // Constructor
    public Usuario(String nombre, String email, String password, int altura, int edad, int peso) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.altura = altura;
        this.edad = edad;
        this.peso = peso;
    }

    //Getter
    public String getId() { return objectId; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getContraseña() { return password; }
    public int getAltura() { return altura; }
    public int getEdad() { return edad; }
    public int getPeso() { return peso; }

    // Setter
    public void setId(String id) { this.objectId = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setAltura(int altura) { this.altura = altura; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setPeso(int peso) { this.peso = peso; }
    public void setEmail(String email) { this.email = email; }
    public void setContraseña(String contraseña) { this.password = contraseña; }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre=" + nombre +
                ", email=" + email +
                ", contrase\u00f1a=" + password +
                ", id=" + objectId +
                ", altura=" + altura +
                ", edad=" + edad +
                ", peso=" + peso +
                '}';
    }
}