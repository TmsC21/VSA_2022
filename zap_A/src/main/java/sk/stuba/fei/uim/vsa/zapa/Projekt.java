package sk.stuba.fei.uim.vsa.zapa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Projekt {
    static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("projektPU");


    public boolean publicationUpdate(String isbn, Double price, String name) {
        EntityManager em = managerFactory.createEntityManager();
        Publication publication = em.find(Publication.class, isbn);

        if (publication == null) {
            Publication newPublication = new Publication(isbn, price, name);
            persist(newPublication);
            return true;
        }
        EntityTransaction transaction = em.getTransaction();
        try {
            if(publication.getName() != null && publication.getName().equals(name)){
                return false;
            }
            if (name != null && publication.getName() == null) {
                transaction.begin();
                publication.setName(name);
                transaction.commit();
            }
            if (price != null) {
                transaction.begin();
                publication.setPrice(price);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public void priceListUpdate(Map<String, Double> priceList) {
        priceList.forEach((key, value) -> publicationUpdate(key, value, ""));
    }

    public void create() {
        Publication publication1 = new Publication();
        publication1.setPrice(10);

        Publication publication2 = new Publication();
        publication2.setName("Second");
        publication2.setPrice(15);

        Publication publication3 = new Publication();
        publication3.setName("Third");
        publication3.setPrice(13);

        persist(publication1, publication2, publication3);
    }

    public void persist(Object... entities) {
        EntityManager em = managerFactory.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            for (Object entity : entities) {
                em.persist(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
