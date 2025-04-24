
package transportate.modelo;

import java.util.Date;
import java.util.List;

public class Entrenamiento {
    
    private String objectId;
    private int duracion;
    private int intensidad;

    // Constructor
    public Entrenamiento(int duracion, int intensidad) {
        this.duracion = duracion;
        this.intensidad = intensidad;
    }

    //Getter
    public String getId() { return objectId; }
    public int getDuracion() { return duracion; }
    public int getIntensidad(){ return intensidad; }

    //Setter
    public void setId(String id) { this.objectId = id; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setIntensidad(int intensidad){ this.intensidad = intensidad; }

    @Override
    public String toString() {
        return "Entrenamiento{" + 
                "id=" + objectId + 
                ", duracion=" + duracion +  
                ", intensidad=" + intensidad +  
                '}';
    }
}
