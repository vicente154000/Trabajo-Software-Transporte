package modelo;
import modelo.Ubicacion;

public class Ejercicio {

    private String objectId;
    private String nombre;
    private String descripcion;
    private int duracion;
    private int intensidad;
    private String nombreUbicacion;

    // Constructor
    public Ejercicio(String nombre, String descripcion, int duracion, int intensidad, String nombreUbicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.intensidad = intensidad;
        this.nombreUbicacion = nombreUbicacion;
    }

    //Getter
    public String getId() { return objectId; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracion() { return duracion; }
    public int getIntensidad() { return intensidad; }
    public String getUbicacion() { return nombreUbicacion; }

    //Setter
    public void setId(String id) { this.objectId = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setIntensidad(int intensidad) { this.intensidad = intensidad; }
    public void setUbicacion(String nombreUbicacion) { this.nombreUbicacion = nombreUbicacion; }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "id=" + objectId +
                ", nombre=" + nombre +
                ", descripcion=" + descripcion +
                ", duracion=" + duracion +
                ", intensidad=" + intensidad +
                ", ubicacion=" + nombreUbicacion + '}';
    }
}
