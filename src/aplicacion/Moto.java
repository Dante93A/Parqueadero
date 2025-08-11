// Archivo: Motocicleta.java
package aplicacion;

public class Moto extends Vehiculo {
    private int cilindraje;

    public Moto(String placa, String marca, String modelo, int cilindraje) {
        // Llama al constructor de la clase padre (Vehiculo)
        super(placa, marca, modelo);
        this.cilindraje = cilindraje;
    }

    @Override
    public String toString() {
        return "Motocicleta -> " + super.toString() + ", Cilindraje: " + cilindraje + "cc";
    }
}