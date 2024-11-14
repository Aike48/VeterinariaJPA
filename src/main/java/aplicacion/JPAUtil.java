package aplicacion;

import jakarta.persistence.*;

public class JPAUtil {

    private static final String UNIDAD_DE_PERSISTENCIA = "PruebaHibernate";
    private static EntityManagerFactory factory;

    // Obtener una instancia de EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            synchronized (JPAUtil.class) {
                if (factory == null) {
                    try {
                        factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA);
                    } catch (PersistenceException e) {
                        System.err.println("Error al crear EntityManagerFactory: " + e.getMessage());
                        throw e; // Rethrow the exception or handle it as needed
                    }
                }
            }
        }
        return factory;
    }

    // Cerrar EntityManagerFactory cuando ya no sea necesario
    public static void shutdown() {
        if (factory != null) {
            try {
                factory.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar EntityManagerFactory: " + e.getMessage());
            } finally {
                factory = null;
            }
        }
    }
}


