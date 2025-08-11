package aplicacion;

public class Carro extends Vehiculo {
    private String tipoCombustible;

    public Carro(String placa, String marca, String modelo, String tipoCombustible) {
        // Llama al constructor de la clase padre (Vehiculo)
        super(placa, marca, modelo);
        this.tipoCombustible = tipoCombustible;
    }

    @Override
    public String toString() {
        return "Carro -> " + super.toString() + ", Combustible: " + tipoCombustible;
    }
}