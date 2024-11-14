package clases;

import java.util.List;
import javax.swing.JOptionPane;
import dao.ProductoDao;
import entidades.Producto;

public class GestionProductos {

    ProductoDao miProductoDao = new ProductoDao();

    public GestionProductos() {
        String menu = "MENU DE OPCIONES - GESTION PRODUCTOS\n\n";
        menu += "1. Registrar Producto\n";
        menu += "2. Consultar Producto\n";
        menu += "3. Consultar Lista de Productos\n";
        menu += "4. Comprar Productos\n";
        menu += "5. Actualizar Producto\n";
        menu += "6. Eliminar Producto\n";
        menu += "7. Salir\n";

        int opc = 0;

        while (opc != 7) {
            try {
                opc = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (opc) {
                    case 1: registrar(); break;
                    case 2: consultar(); break;
                    case 3: consultarLista(); break;
                    case 4: comprarProductos(); break;
                    case 5: actualizar(); break;
                    case 6: eliminar(); break;
                    case 7: miProductoDao.close(); break;
                    default: System.out.println("Opción inválida, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }

    private void registrar() {
        try {
            Producto miProducto = new Producto();
            miProducto.setIdProducto(null);
            miProducto.setNombreProducto(JOptionPane.showInputDialog("Ingrese el nombre del Producto"));
            miProducto.setPrecioProducto(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del Producto")));

            System.out.println(miProductoDao.registrarProducto(miProducto));
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: Precio inválido. Por favor, ingrese un número válido.");
        }
    }

    private void consultar() {
        try {
            Long idProducto = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del producto"));

            Producto miProducto = miProductoDao.consultarProducto(idProducto);

            if (miProducto != null) {
                System.out.println(miProducto);
            } else {
                System.out.println("No se encontró el Producto.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido. Por favor, ingrese un número válido.");
        }
    }

    private void consultarLista() {
        System.out.println("Lista de Productos");
        List<Producto> listaProductos = miProductoDao.consultarListaProductos();

        for (Producto producto : listaProductos) {
            System.out.println(producto);
        }
    }

    private void actualizar() {
        try {
            Long idProducto = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del producto para actualizar"));
            Producto miProducto = miProductoDao.consultarProducto(idProducto);

            if (miProducto != null) {
                System.out.println(miProducto);
                miProducto.setNombreProducto(JOptionPane.showInputDialog("Ingrese el nuevo nombre del Producto"));
                miProducto.setPrecioProducto(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del Producto")));

                System.out.println(miProductoDao.actualizarProducto(miProducto));
            } else {
                System.out.println("No se encontró el producto.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: Precio o ID inválido. Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void eliminar() {
        try {
            Long idProducto = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del Producto para eliminar"));
            Producto miProducto = miProductoDao.consultarProducto(idProducto);

            if (miProducto != null) {
                System.out.println(miProducto);
                System.out.println(miProductoDao.eliminarProducto(miProducto));
            } else {
                System.out.println("No se encontró el Producto.");
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Error: ID inválido. Por favor, ingrese un número válido.");
        }
    }

    private void comprarProductos() {
        Long personaId;
        Long productoId;
        int opc = 0;

        do {
            try {
                personaId = Long.parseLong(JOptionPane.showInputDialog("Ingrese el documento de la persona"));
                productoId = Long.parseLong(JOptionPane.showInputDialog("Ingrese el código del producto"));

                String resultado = miProductoDao.registrarCompra(personaId, productoId);
                System.out.println(resultado);
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese valores numéricos válidos.");
            } catch (Exception e) {
                System.out.println("Error al registrar la compra: " + e.getMessage());
            }

            opc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si desea agregar otro producto, otro número para salir"));
        } while (opc == 1);
    }
}
