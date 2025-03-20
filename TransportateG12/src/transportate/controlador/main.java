
package transportate.controlador;

public class main {
    
    public static void main(String[] args) {
        Ubicacion aula221 = new Ubicacion("Aula 221", 40.7128, -74.0060);
        Ubicacion zonaSofas = new Ubicacion("Zona de sofás", 40.7135, -74.0055);

        System.out.println("Distancia entre " + aula221.getNombre() + " y " + zonaSofas.getNombre() + ": " +
                aula221.calcularDistancia(zonaSofas) + " km");
    }
}
