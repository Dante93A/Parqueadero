package aplicacion;

import java.time.LocalDateTime;

public class  Vehiculo {

	// Atributos protegidos para que las clases hijas puedan acceder a ellos
    protected String placa;
    protected String marca;
    protected String modelo;
    protected LocalDateTime horaEntrada;

    // Constructor
    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = LocalDateTime.now(); // Se establece la hora actual al crear el objeto
    }

    // Getters para acceder a los atributos
    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Marca: " + marca + ", Modelo: " + modelo + ", Hora Entrada: " + horaEntrada.toLocalTime();
    }
}

