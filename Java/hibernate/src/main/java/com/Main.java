package com;

import com.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DemoJPA");
        EntityManager em = emf.createEntityManager();
        System.out.println(
                em.getMetamodel().getEntities()
        );

        Category cat = new Category("Super Badass");

        em.getTransaction().begin();
        em.persist(cat);
        em.getTransaction().commit();

    }
}
