
package transportate.modelo;

public class Ejercicio {
    
    private int id;
    private String nombre;
    private String descripcion;
    private int duracion;
    private int intensidad;
    private Ubicacion ubicacion;

    // Constructor
    public Ejercicio(int id, String nombre, String descripcion, int duracion, int intensidad, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.intensidad = intensidad;
        this.ubicacion = ubicacion;
    }

    //Getter
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracion() { return duracion; }
    public int getIntensidad() { return intensidad; }
    public Ubicacion getUbicacion() { return ubicacion; }
    
    //Setter
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setIntensidad(int intensidad) { this.intensidad = intensidad; }
    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() {
        return "Ejercicio{" + 
                "id=" + id + 
                ", nombre=" + nombre + 
                ", descripcion=" + descripcion + 
                ", duracion=" + duracion + 
                ", intensidad=" + intensidad + 
                ", ubicacion=" + ubicacion + '}';
    }
}
