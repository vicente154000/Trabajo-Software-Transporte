package modelo;
import android.os.Parcelable;

public class Ejercicio implements Parcelable {

    private String objectId;
    private String nombre;
    private String descripcion;
    private int duracion;
    private int intensidad;
    private String nombreUbicacion;
    private String URLimagen;

    // Constructor
    public Ejercicio(String nombre, String descripcion, int duracion, int intensidad, String nombreUbicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.intensidad = intensidad;
        this.nombreUbicacion = nombreUbicacion;
    }

    // Constructor desde Parcel
    protected Ejercicio(android.os.Parcel in) {
        objectId = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        duracion = in.readInt();
        intensidad = in.readInt();
        nombreUbicacion = in.readString();
    }

    public static final Creator<Ejercicio> CREATOR = new Creator<Ejercicio>() {
        @Override
        public Ejercicio createFromParcel(android.os.Parcel in) {
            return new Ejercicio(in);
        }

        @Override
        public Ejercicio[] newArray(int size) {
            return new Ejercicio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(objectId);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeInt(duracion);
        dest.writeInt(intensidad);
        dest.writeString(nombreUbicacion);
    }

    //Getter
    public String getId() { return objectId; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getDuracion() { return duracion; }
    public int getIntensidad() { return intensidad; }
    public String getUbicacion() { return nombreUbicacion; }
    public String getImagen() {
        return URLimagen;
    }

    //Setter
    public void setId(String id) { this.objectId = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setIntensidad(int intensidad) { this.intensidad = intensidad; }
    public void setUbicacion(String nombreUbicacion) { this.nombreUbicacion = nombreUbicacion; }
    public void setImagen(String imagenEjercicio) {
        this.URLimagen = imagenEjercicio;
    }


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
