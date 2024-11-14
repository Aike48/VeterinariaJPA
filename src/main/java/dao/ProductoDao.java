package dao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import aplicacion.JPAUtil;
import entidades.Persona;
import entidades.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProductoDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarProducto(Producto miProducto) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(miProducto);
            entityManager.getTransaction().commit();
            resp = "Producto Registrado!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede registrar el Producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public Producto consultarProducto(Long idProducto) {
        return entityManager.find(Producto.class, idProducto);
    }

    public List<Producto> consultarListaProductos() {
        Query query = entityManager.createQuery("SELECT p FROM Producto p");
        return query.getResultList();
    }

    public String actualizarProducto(Producto miProducto) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(miProducto);
            entityManager.getTransaction().commit();
            resp = "Producto Actualizado!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede actualizar el Producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public String registrarCompra(Long personaId, Long productoId) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            Persona persona = entityManager.find(Persona.class, personaId);
            Producto producto = entityManager.find(Producto.class, productoId);

            if (persona == null || producto == null) {
                throw new Exception("Persona o producto no encontrados.");
            }

            persona.getListaProductos().add(producto);
            entityManager.merge(persona);
            entityManager.getTransaction().commit();
            resp = "Se realizó la compra del producto!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede registrar la compra del Producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public String eliminarProducto(Producto miProducto) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            miProducto = entityManager.find(Producto.class, miProducto.getIdProducto());
            if (miProducto != null) {
                entityManager.remove(miProducto);
                entityManager.getTransaction().commit();
                resp = "Producto Eliminado!";
            } else {
                resp = "Producto no encontrado!";
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede eliminar el Producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }
}
