package clases;

import dao.MascotaDao;
import entidades.Mascota;
import entidades.Persona;
import javax.swing.JOptionPane;
import java.util.List;

public class GestionMascotas {

    MascotaDao miMascotaDao = new MascotaDao();

    public GestionMascotas() {
        String menu = "MENU DE OPCIONES - GESTION MASCOTAS\n\n";
        menu += "1. Registrar Mascota\n";
        menu += "2. Consultar Mascota\n";
        menu += "3. Consultar Lista de Mascotas\n";
        menu += "4. Consultar Lista de Mascotas por sexo\n";
        menu += "5. Actualizar Mascota\n";
        menu += "6. Eliminar Mascota\n";
        menu += "7. Salir\n";

        int opc = 0;

        while (opc != 7) {
            try {
                opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opc) {
                    case 1 -> registrar();
                    case 2 -> consultar();
                    case 3 -> consultarLista();
                    case 4 -> consultarListaPorSexo();
                    case 5 -> actualizar();
                    case 6 -> eliminar();
                    case 7 -> miMascotaDao.close();
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registrar() {
        try {
            Mascota miMascota = new Mascota();
            miMascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
            miMascota.setRaza(JOptionPane.showInputDialog("Ingrese la raza de la mascota"));
            miMascota.setColorMascota(JOptionPane.showInputDialog("Ingrese el color de la mascota"));
            miMascota.setSexo(JOptionPane.showInputDialog("Ingrese el sexo de la mascota"));

            Long idDuenio = Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento del dueño"));
            Persona duenio = new Persona();
            duenio.setIdPersona(idDuenio);
            miMascota.setDuenio(duenio);

            System.out.println(miMascotaDao.registrarMascota(miMascota));
            System.out.println();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID del dueño inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultar() {
        try {
            Long idMascota = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la Mascota"));
            Mascota miMascota = miMascotaDao.consultarMascota(idMascota);
            if (miMascota != null) {
                System.out.println(miMascota);
            } else {
                System.out.println("No se encontró la mascota");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID de mascota inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println();
    }

    private void consultarLista() {
        System.out.println("Lista de Mascotas:");
        List<Mascota> listaMascotas = miMascotaDao.consultarListaMascotas();
        listaMascotas.forEach(System.out::println);
    }

    private void consultarListaPorSexo() {
        String sexo = JOptionPane.showInputDialog("Ingrese el sexo de la Mascota");
        List<Mascota> listaMascotas = miMascotaDao.consultarListaMascotasPorSexo(sexo);
        if (listaMascotas.isEmpty()) {
            System.out.println("No se encontraron mascotas con el sexo indicado.");
        } else {
            listaMascotas.forEach(System.out::println);
        }
    }

    private void actualizar() {
        try {
            Long idMascota = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la Mascota para actualizar"));
            Mascota miMascota = miMascotaDao.consultarMascota(idMascota);
            if (miMascota != null) {
                miMascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
                miMascota.setRaza(JOptionPane.showInputDialog("Ingrese la raza de la mascota"));
                miMascota.setColorMascota(JOptionPane.showInputDialog("Ingrese el color de la mascota"));
                miMascota.setSexo(JOptionPane.showInputDialog("Ingrese el sexo de la mascota"));

                Long idDuenio = Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento del dueño"));
                Persona duenio = new Persona();
                duenio.setIdPersona(idDuenio);
                miMascota.setDuenio(duenio);

                System.out.println(miMascotaDao.actualizarMascota(miMascota));
            } else {
                System.out.println("No se encontró la mascota");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Datos inválidos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        try {
            Long idMascota = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la Mascota para eliminar"));
            Mascota miMascota = miMascotaDao.consultarMascota(idMascota);
            if (miMascota != null) {
                System.out.println(miMascotaDao.eliminarMascota(miMascota));
            } else {
                System.out.println("No se encontró la mascota");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID de mascota inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println();
    }
}

