package se.rich.grtiming.system.manager.interfaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class Manager {

    private SessionFactory sessionFactory;
    protected Session session;

    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate_cfg.xml").build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransaction() {
        if (null == session) {
            setup();
        }
        return session.beginTransaction();
    }

    public Session getSession() {
        return this.session;
    }

    public void exit(boolean rollback) {
        if (rollback) {
            getTransaction().rollback();
        } else {
            getTransaction().commit();
        }
        session.close();
        sessionFactory.close();
    }

}
