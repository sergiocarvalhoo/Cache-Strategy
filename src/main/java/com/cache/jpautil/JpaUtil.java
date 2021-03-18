package com.cache.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static final String PERSISTENCE_UNIT_NAME = "database";
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.out.println("Aconteceu algum problema ao carregar a EntityManagerFactory: "+ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager createEntityManager() {
        if (!entityManagerFactory.isOpen())
            throw new RuntimeException("A EntityManagerFactory está fechada!");
        return entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("A EntityManagerFactory finalizada com Sucesso !");
        }
    }

}