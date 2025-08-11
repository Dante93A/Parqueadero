// Archivo: Parqueadero.java
package aplicacion;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    private List<Vehiculo> vehiculosEstacionados;
    private static final double TARIFA_AUTOMOVIL_HORA = (11400/60); //
    private static final double TARIFA_MOTOCICLETA_HORA = (5700/60);
    private static final double TARIFA_CAMION_HORA = (15000/60);

    public Parqueadero() {
        this.vehiculosEstacionados = new ArrayList<>();
    }

    /**
     * Registra la entrada de un vehículo al parqueadero.
     */
    public void registrarEntrada(Vehiculo vehiculo) {
        vehiculosEstacionados.add(vehiculo);
        System.out.println("✅ Vehículo con placa " + vehiculo.getPlaca() + " ha ingresado.");
    }

    /**
     * Registra la salida de un vehículo y calcula el costo.
     * @param placa La placa del vehículo que sale.
     * @return El costo del estacionamiento o -1 si el vehículo no se encuentra.
     */
    public double registrarSalida(String placa) {
        Vehiculo vehiculoParaSalir = null;
        for (Vehiculo v : vehiculosEstacionados) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                vehiculoParaSalir = v;
                break;
            }
        }

        if (vehiculoParaSalir != null) {
            LocalDateTime horaSalida = LocalDateTime.now();
            Duration tiempoEstadia = Duration.between(vehiculoParaSalir.getHoraEntrada(), horaSalida);
            long horas = tiempoEstadia.toMinutes() + 1; // Se cobra por hora ToHours o fracción de hora ToMinutes

            double costo = 0.0;
            // 
            if (vehiculoParaSalir instanceof Carro) {
                costo = horas * TARIFA_AUTOMOVIL_HORA;
            } else if (vehiculoParaSalir instanceof Moto) {
                costo = horas * TARIFA_MOTOCICLETA_HORA;
            } else if (vehiculoParaSalir instanceof Camion) {
                costo = horas * TARIFA_CAMION_HORA;
            }

            vehiculosEstacionados.remove(vehiculoParaSalir);
            System.out.println("✅ Vehículo con placa " + placa + " ha salido. Tiempo: " + (tiempoEstadia.toMinutes()) + " minutos.");
            System.out.println("💲 Costo total: $" + costo);
            return costo;
        } else {
            System.out.println("❌ Error: Vehículo con placa " + placa + " no encontrado.");
            return -1; // 
        }
    }

    /**
     * 
     */
    public void consultarEstado() {
        System.out.println("\n--- Estado del Parqueadero ---");
        if (vehiculosEstacionados.isEmpty()) {
            System.out.println("El parqueadero está vacío.");
        } else {
            System.out.println("Vehículos estacionados: " + vehiculosEstacionados.size());
            for (Vehiculo v : vehiculosEstacionados) {
                System.out.println("-> " + v.toString());
            }
        }
        System.out.println("----------------------------\n");
    }



/**
 * Devuelve la lista de vehículos para que la interfaz pueda mostrarlos.
 * @return Una copia de la lista de vehículos estacionados.
 */
public List<Vehiculo> getVehiculosEstacionados() {
    return new ArrayList<>(this.vehiculosEstacionados);
}
}