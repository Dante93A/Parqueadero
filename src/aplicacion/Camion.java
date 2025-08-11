// Archivo: Camion.java
package aplicacion;

public class Camion extends Vehiculo {
    private double capacidadCarga; // En toneladas

    public Camion(String placa, String marca, String modelo, double capacidadCarga) {
       
        super(placa, marca, modelo);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String toString() {
        return "Camión -> " + super.toString() + ", Capacidad de Carga: " + capacidadCarga + "t";
    }
}