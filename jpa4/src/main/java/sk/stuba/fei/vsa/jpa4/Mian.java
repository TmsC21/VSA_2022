package sk.stuba.fei.vsa.jpa4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collections;

public class Mian {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        Person person = new Person();
        person.setName("Janko");

        Subject prednaska1 = new Subject();
        prednaska1.setNazov("VSA");
        prednaska1.setRocnik("Treti");
        prednaska1.setKredity(10);
        prednaska1.setGarant(Collections.singletonList(person));

        Subject prednaska2 = new Subject();
        prednaska2.setNazov("MAT");
        prednaska2.setRocnik("Prvy");
        prednaska2.setKredity(6);
        prednaska1.setGarant(Collections.singletonList(person));

        person.setPrednasky(new ArrayList<>());
        person.getPrednasky().add(prednaska1);
        person.getPrednasky().add(prednaska2);

        persist(managerFactory,person,prednaska1,prednaska2);

    }

    public static void persist(EntityManagerFactory managerFactory, Object... entities){
        EntityManager manager = managerFactory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        for (Object entity: entities){
            manager.persist(entity);
        }
        transaction.commit();
        manager.close();
    }
}
