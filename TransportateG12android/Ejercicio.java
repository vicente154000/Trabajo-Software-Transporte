package modelo;

public class Ejercicio {
    private int id;
    private String nombre;
    private String descripcion;
    private int duracion;
    private int intensidad;
    private Ubicacion ubicacion;

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
