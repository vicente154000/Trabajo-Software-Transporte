
package transportate.modelo;

import java.util.Date;
import java.util.List;

public class Entrenamiento {
    private int id;
    private int idUsuario;
    private int duracion;
    private int caloriasQuemadas;
    private Date fecha;
    private List<Ejercicio> ejercicios;
    
    public Entrenamiento(int idUsuario, int duracion, int caloriasQuemadas, Date fecha, List<Ejercicio> ejercicios){
        this.idUsuario = idUsuario;
        this.duracion = duracion;
        this.caloriasQuemadas = caloriasQuemadas;
        this.fecha = fecha;
        this.ejercicios = ejercicios;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setCaloriasQuemadas(int caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
    
    
}
