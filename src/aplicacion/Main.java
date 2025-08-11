// Archivo: Main.java (Reemplazar todo el contenido)
package aplicacion;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        //
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazParqueadero().setVisible(true);
            }
        });
    }
}