
package transportate.modelo;

public class Ejercicio {
    private int id;
    private String nombre;
    private String descripcion;
    private int duracion;
    private int intensidad;
    private Ubicacion ubicacion;
    
    public Ejercicio(String nombre, String descripcion, int duracion, int intensidad, Ubicacion ubicacion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion; // Rango de 1-10, siendo 10 el ejercicio mas largo
        this.intensidad = intensidad; // Rango de 1-3
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
}
