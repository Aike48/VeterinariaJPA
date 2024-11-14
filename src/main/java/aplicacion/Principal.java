package aplicacion;

import clases.GestionMascotas;
import clases.GestionPersonas;
import clases.GestionProductos;
import javax.swing.*;

public class Principal {

    public static void main(String[] args) {

        String menu = "MENU DE OPCIONES\n\n";
        menu += "1. Gestionar Personas\n";
        menu += "2. Gestionar Mascotas\n";
        menu += "3. Gestionar Productos\n";
        menu += "4. Salir\n\n";

        int opc = 0;

        while (opc != 4) {
            try {
                String input = JOptionPane.showInputDialog(menu);

                if (input != null) {
                    opc = Integer.parseInt(input);

                    switch (opc) {
                        case 1:
                            new GestionPersonas();
                            break;
                        case 2:
                            new GestionMascotas();
                            break;
                        case 3:
                            new GestionProductos();
                            break;
                        case 4:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, ingrese una opción válida.");
                            break;
                    }
                } else {
                    opc = 4;
                    System.out.println("Saliendo...");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Por favor, ingrese un número válido.");
            }
        }
    }
}


