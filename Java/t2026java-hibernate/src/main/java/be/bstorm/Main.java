package be.bstorm;

import be.bstorm.dal.entities.PlaneEntity;
import be.bstorm.lib.persistence.JpaPersistenceUnit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        try(
                EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new JpaPersistenceUnit(), new HashMap());
                EntityManager em = emf.createEntityManager()
            ) {

            TypedQuery<PlaneEntity> query = em.createQuery("select new Plane(p.id) from Plane p", PlaneEntity.class);

            List<PlaneEntity> planes = query.getResultList();

            for(PlaneEntity plane : planes) {
                System.out.println(plane.getImma());
            }

        }
    }
}
