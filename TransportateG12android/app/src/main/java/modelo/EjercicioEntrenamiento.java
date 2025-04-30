package modelo;

public class EjercicioEntrenamiento {
    private String objectId;
    private String entrenamientoId;
    private String ejercicioId;

    public EjercicioEntrenamiento(String entrenamientoId, String ejercicioId){
        this.entrenamientoId = entrenamientoId;
        this.ejercicioId = ejercicioId;
    }

    public String getId(){
        return objectId;
    }

    public String getEntrenamientoId(){
        return entrenamientoId;
    }

    public String getEjercicioId(){
        return ejercicioId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setEntrenamientoId(String entrenamientoId) {
        this.entrenamientoId = entrenamientoId;
    }

    public void setEjercicioId(String ejercicioId) {
        this.ejercicioId = ejercicioId;
    }
}
