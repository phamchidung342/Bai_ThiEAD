package com.fpt.aptech.sis.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT_NAME = "SIS_PU";
    private static EntityManagerFactory factory;
    
    static {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing EntityManagerFactory", e);
        }
    }
    
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    
    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}

