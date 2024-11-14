package dao;

import aplicacion.JPAUtil;
import entidades.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import javax.swing.JOptionPane;
import java.util.List;

public class PersonaDao {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarPersona(Persona miPersona) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(miPersona);
            entityManager.getTransaction().commit();
            resp = "Persona Registrada!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede registrar la Persona", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public Persona consultarPersona(Long idPersona) {
        return entityManager.find(Persona.class, idPersona);
    }

    public List<Persona> consultarListaPersonas() {
        Query query = entityManager.createQuery("SELECT p FROM Persona p");
        return query.getResultList();
    }

    public String actualizarPersona(Persona miPersona) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(miPersona);
            entityManager.getTransaction().commit();
            resp = "Persona Actualizada!";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede actualizar la Persona", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public String eliminarPersona(Persona miPersona) {
        String resp = "";
        try {
            entityManager.getTransaction().begin();
            miPersona = entityManager.find(Persona.class, miPersona.getIdPersona());
            if (miPersona != null) {
                entityManager.remove(miPersona);
                entityManager.getTransaction().commit();
                resp = "Persona Eliminada!";
            } else {
                resp = "Persona no encontrada!";
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "No se puede eliminar la Persona, verifique que no tenga registros pendientes", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return resp;
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }
}
