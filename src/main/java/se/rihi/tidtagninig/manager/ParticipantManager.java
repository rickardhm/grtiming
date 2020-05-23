package se.rihi.tidtagninig.manager;

import se.rihi.tidtagninig.entity.Participant;
import se.rihi.tidtagninig.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class ParticipantManager extends Manager {

    public ParticipantManager() {
        setup();
    }

    public Participant create(Participant participant) {
        session.persist(participant);
        return participant;
    }

    public List<Participant> read() {
        Query q = session.createQuery("from Participant");
        List<Participant> list = q.getResultList();
        return list;
    }

    public Participant findById(String namerQuery, int searchTerm) {
        Query q = session.createNamedQuery(namerQuery);
        q.setParameter("id", searchTerm);
        Participant p = (Participant) q.getSingleResult();
        return p;
    }

    public int getMaxStartNumber(int raceId) {
        Query q = session.createNamedQuery(Participant.FIND_MAX_START_NUMBER);
        q.setParameter("raceId", raceId);
        Object o =  q.getSingleResult();
        int nr = 0;
        if (null != o) {
            nr = (int) o;
        }
        return nr;
    }

    public List<Participant> findByName(String namedQuery, String searchTerm) {
        Query q = session.createNamedQuery(namedQuery);
        q.setParameter("name", searchTerm);
        List<Participant> list = q.getResultList();
        return list;
    }

    public void update(Participant participant) {
        session.update(participant);
        getTransaction().commit();
    }

    public void delete(Participant participant) {
        session.delete(participant);
    }

}
