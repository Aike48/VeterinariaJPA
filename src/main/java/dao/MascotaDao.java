package dao;

import aplicacion.JPAUtil;
import entidades.Mascota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import javax.swing.JOptionPane;
import java.util.List;

public class MascotaDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarMascota(Mascota miMascota) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(miMascota);
            entityManager.getTransaction().commit();
            resp = "Mascota Registrada!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede registrar la mascota. Verifique que el dueño exista", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public Mascota consultarMascota(Long idMascota) {
        return entityManager.find(Mascota.class, idMascota);
    }

    public List<Mascota> consultarListaMascotas() {
        Query query = entityManager.createQuery("SELECT m FROM Mascota m");
        return query.getResultList();
    }

    public List<Mascota> consultarListaMascotasPorSexo(String sexo) {
        String sentencia = "SELECT m FROM Mascota m WHERE m.sexo = :sexoMascota";
        Query query = entityManager.createQuery(sentencia);
        query.setParameter("sexoMascota", sexo);
        return query.getResultList();
    }

    public String actualizarMascota(Mascota miMascota) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(miMascota);
            entityManager.getTransaction().commit();
            resp = "Mascota Actualizada!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede actualizar la mascota. Verifique que el dueño exista", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public String eliminarMascota(Mascota miMascota) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            miMascota = entityManager.find(Mascota.class, miMascota.getIdMascota());
            if (miMascota != null) {
                entityManager.remove(miMascota);
                entityManager.getTransaction().commit();
                resp = "Mascota Eliminada!";
            } else {
                resp = "Mascota no encontrada!";
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede eliminar la mascota", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }
}
