// Archivo: InterfazParqueadero.java (Actualizado)
package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazParqueadero extends JFrame {

    private Parqueadero parqueadero;
    private JTextArea areaEstado;
    private JButton btnRegistrarEntrada;
    private JButton btnRegistrarSalida;

    public InterfazParqueadero() {
        // 1. Inicializar el parqueadero
        parqueadero = new Parqueadero();

        // 2. Configurar la ventana principal (el JFrame)
        setTitle("Gestión de Parqueadero 🅿️");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout(10, 10));

        // 3. Crear los componentes de la interfaz
        // Panel superior para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnRegistrarEntrada = new JButton("Registrar Entrada");
        btnRegistrarSalida = new JButton("Registrar Salida");
        panelBotones.add(btnRegistrarEntrada);
        panelBotones.add(btnRegistrarSalida);

        // Área de texto para mostrar el estado (en el centro)
        areaEstado = new JTextArea();
        areaEstado.setEditable(false);
        areaEstado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaEstado); // Para poder hacer scroll

        // 4. Añadir los componentes a la ventana
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 5. Añadir la lógica a los botones (ActionListeners)
        btnRegistrarEntrada.addActionListener(e -> mostrarDialogoEntrada());

        btnRegistrarSalida.addActionListener(e -> mostrarDialogoSalida());

        // 6. Actualizar el estado inicial
        actualizarAreaEstado();
    }

    private void actualizarAreaEstado() {
        StringBuilder sb = new StringBuilder();
        List<Vehiculo> vehiculos = parqueadero.getVehiculosEstacionados();

        sb.append("--- ESTADO DEL PARQUEADERO ---\n");
        sb.append("Vehículos estacionados: ").append(vehiculos.size()).append("\n\n");

        if (vehiculos.isEmpty()) {
            sb.append("El parqueadero está vacío.");
        } else {
            for (Vehiculo v : vehiculos) {
                sb.append("-> ").append(v.toString()).append("\n");
            }
        }
        areaEstado.setText(sb.toString());
    }

    private void mostrarDialogoEntrada() {
        // Crear un panel para el formulario de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField campoPlaca = new JTextField();
        JTextField campoMarca = new JTextField();
        JTextField campoModelo = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Carro", "Moto", "Camión"});

        panelEntrada.add(new JLabel("Placa:"));
        panelEntrada.add(campoPlaca);
        panelEntrada.add(new JLabel("Marca:"));
        panelEntrada.add(campoMarca);
        panelEntrada.add(new JLabel("Modelo:"));
        panelEntrada.add(campoModelo);
        panelEntrada.add(new JLabel("Tipo de Vehículo:"));
        panelEntrada.add(comboTipo);

        int resultado = JOptionPane.showConfirmDialog(this, panelEntrada, "Registrar Entrada", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String placa = campoPlaca.getText();
            String marca = campoMarca.getText();
            String modelo = campoModelo.getText();
            String tipo = (String) comboTipo.getSelectedItem();

            if (placa.trim().isEmpty() || marca.trim().isEmpty() || modelo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vehiculo nuevoVehiculo = null;
            try {
                switch (tipo) {
                    case "Carro":
                        // --- INICIO DEL CAMBIO ---
                        // Se definen las opciones válidas
                        String[] opcionesCombustible = {"Gasolina", "Diesel", "Hibrido"};
                        // Se crea un menú desplegable en el diálogo
                        String combustible = (String) JOptionPane.showInputDialog(
                                this,
                                "Seleccione el tipo de combustible:",
                                "Tipo de Combustible",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opcionesCombustible,
                                opcionesCombustible[0]
                        );

                        // Si el usuario presiona "Cancelar", combustible será null.
                        if (combustible == null) {
                            return; // No se hace nada si el usuario cancela.
                        }
                        // --- FIN DEL CAMBIO ---
                        
                        nuevoVehiculo = new Carro(placa, marca, modelo, combustible);
                        break;
                    case "Moto":
                        int cilindraje = Integer.parseInt(JOptionPane.showInputDialog(this, "Cilindraje (cc):"));
                        nuevoVehiculo = new Moto(placa, marca, modelo, cilindraje);
                        break;
                    case "Camión":
                        double capacidad = Double.parseDouble(JOptionPane.showInputDialog(this, "Capacidad de carga (ton):"));
                        nuevoVehiculo = new Camion(placa, marca, modelo, capacidad);
                        break;
                }
                
                if (nuevoVehiculo != null) {
                    parqueadero.registrarEntrada(nuevoVehiculo);
                    actualizarAreaEstado();
                    JOptionPane.showMessageDialog(this, "Vehículo registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor numérico no válido para cilindraje o capacidad.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                // Ocurre si el usuario cierra el diálogo de cilindraje/capacidad
                return; 
            }
        }
    }

    private void mostrarDialogoSalida() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa del vehículo que sale:");
        if (placa != null && !placa.trim().isEmpty()) {
            double costo = parqueadero.registrarSalida(placa.trim());

            if (costo != -1) {
                JOptionPane.showMessageDialog(this, "Salida registrada.\nCosto total: $" + String.format("%.2f", costo), "Salida Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El vehículo con placa '" + placa + "' no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            actualizarAreaEstado();
        }
    }
}