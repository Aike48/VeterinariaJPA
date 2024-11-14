package clases;

import dao.MascotaDao;
import dao.PersonaDao;
import entidades.Mascota;
import entidades.Nacimiento;
import entidades.Persona;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class GestionPersonas {

    PersonaDao miPersonaDao = new PersonaDao();

    public GestionPersonas() {
        String menu = "MENU DE OPCIONES - GESTION PERSONAS\n\n";
        menu += "1. Registrar Persona\n";
        menu += "2. Consultar Persona\n";
        menu += "3. Consultar Lista de Personas\n";
        menu += "4. Actualizar Persona\n";
        menu += "5. Eliminar Persona\n";
        menu += "6. Salir\n";

        int opc = 0;

        while(opc != 6) {
            try {
                opc = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (opc) {
                    case 1: registrar(); break;
                    case 2: consultar(); break;
                    case 3: consultarLista(); break;
                    case 4: actualizarNombre(); break;
                    case 5: eliminar(); break;
                    case 6: miPersonaDao.close(); break;
                    default: System.out.println("Opción inválida, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }

    private void registrar() {
        try {
            Persona miPersona = new Persona();
            miPersona.setIdPersona(Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento de la persona")));
            miPersona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la Persona"));
            miPersona.setTelefono(JOptionPane.showInputDialog("Ingrese el teléfono de la Persona"));
            miPersona.setProfesion(JOptionPane.showInputDialog("Ingrese la profesión de la Persona"));
            miPersona.setTipo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo")));

            miPersona.setNacimiento(obtenerDatosNacimiento());

            int opc;
            do {
                opc = Integer.parseInt(JOptionPane.showInputDialog("Desea Registrar una Mascota?\n1. SI\n2. NO"));
                if (opc == 1) {
                    miPersona.getListaMascotas().add(obtenerDatosMascota(miPersona));
                }
            } while (opc != 2);

            System.out.println(miPersonaDao.registrarPersona(miPersona));
            System.out.println(miPersona);
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error en el ingreso de datos numéricos. Por favor, revise los valores ingresados.");
        }
    }

    private Mascota obtenerDatosMascota(Persona miPersona) {
        Mascota miMascota = new Mascota();
        miMascota.setIdMascota(null);
        miMascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
        miMascota.setRaza(JOptionPane.showInputDialog("Ingrese la raza de la mascota"));
        miMascota.setColorMascota(JOptionPane.showInputDialog("Ingrese el color de la mascota"));
        miMascota.setSexo(JOptionPane.showInputDialog("Ingrese el sexo de su mascota"));
        miMascota.setDuenio(miPersona);

        return miMascota;
    }

    private Nacimiento obtenerDatosNacimiento() {
        try {
            int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DIA de Nacimiento"));
            int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el MES de Nacimiento"));
            int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Año de Nacimiento"));

            Nacimiento datosNacimiento = new Nacimiento();
            datosNacimiento.setIdNacimiento(null);
            datosNacimiento.setFechaNacimiento(LocalDate.of(anio, mes, dia));
            datosNacimiento.setCiudadNacimiento(JOptionPane.showInputDialog("Ingrese la ciudad de Nacimiento"));
            datosNacimiento.setDepartamentoNacimiento(JOptionPane.showInputDialog("Ingrese el departamento de Nacimiento"));
            datosNacimiento.setPaisNacimiento(JOptionPane.showInputDialog("Ingrese el país de Nacimiento"));

            return datosNacimiento;
        } catch (NumberFormatException e) {
            System.out.println("Error en la entrada de datos de nacimiento. Por favor, revise los valores ingresados.");
            return null;
        }
    }

    private void consultar() {
        try {
            Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la Persona"));

            Persona miPersona = miPersonaDao.consultarPersona(idPersona);

            if (miPersona != null) {
                System.out.println(miPersona);
            } else {
                System.out.println("No se encontró la persona.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido. Por favor, ingrese un número válido.");
        }
    }

    private void consultarLista() {
        System.out.println("Lista de Personas");
        List<Persona> listaPersonas = miPersonaDao.consultarListaPersonas();

        for (Persona persona : listaPersonas) {
            System.out.println(persona);
        }
    }

    private void actualizarNombre() {
        try {
            Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la Persona para actualizar su nombre"));
            Persona miPersona = miPersonaDao.consultarPersona(idPersona);

            if (miPersona != null) {
                System.out.println(miPersona);
                miPersona.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre de la Persona"));

                int opc;
                do {
                    opc = Integer.parseInt(JOptionPane.showInputDialog("Desea Registrar una Mascota?\n1. SI\n2. NO"));
                    if (opc == 1) {
                        miPersona.getListaMascotas().add(obtenerDatosMascota(miPersona));
                    }
                } while (opc != 2);

                System.out.println(miPersonaDao.actualizarPersona(miPersona));
            } else {
                System.out.println("No se encontró la Persona.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido. Por favor, ingrese un número válido.");
        }
    }

    private void eliminar() {
        try {
            Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la Persona para eliminar"));
            Persona miPersona = miPersonaDao.consultarPersona(idPersona);

            if (miPersona != null) {
                System.out.println(miPersona);
                System.out.println(miPersonaDao.eliminarPersona(miPersona));
            } else {
                System.out.println("No se encontró la Persona.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido. Por favor, ingrese un número válido.");
        }
    }
}
