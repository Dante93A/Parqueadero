// Archivo: Main.java
package aplicacion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parqueadero miParqueadero = new Parqueadero();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- GESTIÓN DE PARQUEADERO ---");
            System.out.println("1. Registrar Entrada de Vehículo");
            System.out.println("2. Registrar Salida de Vehículo");
            System.out.println("3. Consultar Estado del Parqueadero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer del scanner

                switch (opcion) {
                    case 1:
                        registrarEntradaInteractivo(scanner, miParqueadero);
                        break;
                    case 2:
                        registrarSalidaInteractivo(scanner, miParqueadero);
                        break;
                    case 3:
                        miParqueadero.consultarEstado();
                        break;
                    case 4:
                        salir = true;
                        System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debe ingresar un número.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void registrarEntradaInteractivo(Scanner scanner, Parqueadero parqueadero) {
        
        System.out.println("\n--- Registrar Nuevo Vehículo ---");
        System.out.print("Tipo de vehículo (1: Carro, 2: Moto, 3: Camión): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Ingrese la placa: ");
        String placa = scanner.nextLine();
        System.out.print("Ingrese la marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el modelo: ");
        String modelo = scanner.nextLine();

        Vehiculo nuevoVehiculo = null;

        try {
            switch (tipo) {
                case 1: // 
                    System.out.print("Tipo de combustible (Gasolina, Diesel, Eléctrico): ");
                    String combustible = scanner.nextLine();
                    nuevoVehiculo = new Carro(placa, marca, modelo, combustible);
                    break;
                case 2: // 
                    System.out.print("Cilindraje (cc): ");
                    int cilindraje = scanner.nextInt();
                    scanner.nextLine();
                    nuevoVehiculo = new Moto(placa, marca, modelo, cilindraje);
                    break;
                case 3:
                    System.out.print("Capacidad de carga (toneladas): ");
                    double carga = scanner.nextDouble();
                    scanner.nextLine();
                    nuevoVehiculo = new Camion(placa, marca, modelo, carga);
                    break;
                default:
                    System.out.println("❌ Tipo de vehículo no válido.");
                    return;
            }
            parqueadero.registrarEntrada(nuevoVehiculo);

        } catch (InputMismatchException e) {
            System.out.println("❌ Error: El dato ingresado no tiene el formato correcto.");
            scanner.nextLine();
        }
    }

    private static void registrarSalidaInteractivo(Scanner scanner, Parqueadero parqueadero) {
        System.out.println("\n--- Registrar Salida de Vehículo ---");
        System.out.print("Ingrese la placa del vehículo que sale: ");
        String placa = scanner.nextLine();
        parqueadero.registrarSalida(placa);
    }
}