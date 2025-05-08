package modelo;

public class Ubicacion {

    private String objectId;
    private String nombre;      // Nombre de la ubicación
    private double latitud;     // Coordenada de latitud
    private double longitud;    // Coordenada de longitud

    // Constructor
    public Ubicacion(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters
    public String getId() { return objectId; }
    public String getNombre() { return nombre; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }

    // Setters
    public void setId(String objectId) { this.objectId = objectId; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double calcularDistancia(Ubicacion otraUbicacion) {
        final int RADIO_TIERRA = 6371;

        double difLatitud = Math.toRadians(otraUbicacion.getLatitud() - this.latitud);
        double difLongitud = Math.toRadians(otraUbicacion.getLongitud() - this.longitud);

        double a = Math.sin(difLatitud / 2) * Math.sin(difLatitud / 2)
                + Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(otraUbicacion.getLatitud()))
                * Math.sin(difLongitud / 2) * Math.sin(difLongitud / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA * c;
    }

    @Override
    public String toString() {
        return nombre;
    }
}