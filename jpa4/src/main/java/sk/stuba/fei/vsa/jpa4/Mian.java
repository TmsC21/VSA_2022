package sk.stuba.fei.vsa.jpa4;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mian {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        create(managerFactory);
        read("Fero", managerFactory);
    }

    public static void read(String meno, EntityManagerFactory managerFactory) {
        EntityManager entityManager = managerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("select s.NAZOV,P2.NAME from SUBJECT s join PERSON_SUBJECT PS on s.ID = PS.cvicenia_ID join PERSON P on P.ID = PS.asistenti_ID join PERSON P2 on P2.ID = s.prednasajuci_id where P.NAME ='" + meno + "'");
        query.getResultList().forEach(o -> {
                    Object[] node = (Object[]) o;
                    System.out.println("Prednasajuci: " + node[1] + "(" + node[0] + ")");
                }
        );
    }

    public static void create(EntityManagerFactory managerFactory) {
        Person person = new Person();
        person.setName("Janko");
        person.setTitul(Titul.Prof);

        Person person1 = new Person();
        person1.setName("Ignac");
        person1.setTitul(Titul.Doc);

        Person person2 = new Person();
        person2.setName("Fero");
        person2.setTitul(Titul.Doc);

        Subject predmet = new Subject();
        predmet.setNazov("VSA");
        predmet.setRocnik("Treti");
        predmet.setKredity(10);

        Subject predmet2 = new Subject();
        predmet2.setNazov("MAT");
        predmet2.setRocnik("Prvy");
        predmet2.setKredity(6);

        person.setPrednasky(Collections.singletonList(predmet));

        person1.setPrednasky(Collections.singletonList(predmet2));
        person1.setCvicenia(Collections.singletonList(predmet2));

        person2.setCvicenia(new ArrayList<>());
        person2.getCvicenia().add(predmet);
        person2.getCvicenia().add(predmet2);

        persist(managerFactory, person, person1, person2, predmet, predmet2);
    }

    public static void persist(EntityManagerFactory managerFactory, Object... entities) {
        EntityManager manager = managerFactory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        for (Object entity : entities) {
            manager.persist(entity);
        }
        transaction.commit();
        manager.close();
    }
}
