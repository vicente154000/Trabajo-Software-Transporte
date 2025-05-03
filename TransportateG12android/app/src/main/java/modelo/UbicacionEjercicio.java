package modelo;

public class UbicacionEjercicio {
    private String objectId;
    private String idUbicacion;
    private String idEjercicio;

    public UbicacionEjercicio(String idUbicacion, String idEjercicio){
        this.idUbicacion = idUbicacion;
        this.idEjercicio = idEjercicio;
    }

    public String getId() {
        return objectId;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public String getIdEjercicio() {
        return idEjercicio;
    }

    public void setId(String objectId) {
        this.objectId = objectId;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

}
