package se.rihi.tidtagninig.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import se.rihi.tidtagninig.entity.Participant;
import se.rihi.tidtagninig.manager.interfaces.ParticipantManagerInterface;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ParticipantManager implements ParticipantManagerInterface {

    protected SessionFactory sessionFactory;
    private Session session;
    EntityManagerFactory factory;

    public ParticipantManager() {
        setup();
    }

    @Override
    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate_cfg.xml").build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exit() {
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void create(Participant participant) {
        if (null != participant) {

            session.persist(participant);
        }
    }

    @Override
    public List<Participant> read() {
        Query q = session.createQuery("from Participant");
        List<Participant> list = q.getResultList();
        return list;
    }

    @Override
    public Participant findById(String namerQuery, int searchTerm) {
        Query q = session.createNamedQuery(namerQuery);
        q.setParameter("id", searchTerm);
        Participant p = (Participant) q.getSingleResult();
        return p;
    }

    @Override
    public List<Participant> findByName(String namedQuery, String searchTerm) {
        Query q = session.createNamedQuery(namedQuery);
        q.setParameter("name", searchTerm);
        List<Participant> list = q.getResultList();
        return list;
    }

    @Override
    public void update(Participant participant) {
        session.update(participant);
    }

    @Override
    public void delete(Participant participant) {
        session.delete(participant);
    }

}
