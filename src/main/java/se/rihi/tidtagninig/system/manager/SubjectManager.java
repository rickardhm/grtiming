package se.rihi.tidtagninig.system.manager;

import se.rihi.tidtagninig.system.entity.Subject;
import se.rihi.tidtagninig.system.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class SubjectManager extends Manager {

    public SubjectManager() {
        setup();
    }

    public void create(Subject subject) {
        session.persist(subject);
    }

    public List<Subject> read() {
        Query q = session.createQuery("from Subject");
        List<Subject> list = q.getResultList();
        return list;
    }

    public Subject findById(String namerQuery, int searchTerm) {
        Query q = session.createNamedQuery(namerQuery);
        q.setParameter("id", searchTerm);
        Subject subject = (Subject) q.getSingleResult();
        return subject;
    }
}
