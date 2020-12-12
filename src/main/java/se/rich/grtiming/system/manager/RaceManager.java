package se.rich.grtiming.system.manager;

import se.rich.grtiming.system.manager.interfaces.Manager;
import se.rich.grtiming.system.entity.Race;

import javax.persistence.Query;
import java.util.List;

public class RaceManager extends Manager {

    public RaceManager() {
        setup();
    }

    public void create(Race race) {
        session.persist(race);
    }

    public List<Race> read() {
        Query q = session.createQuery("from Race");
        return (List<Race>) q.getResultList();
    }

    public Race findById(String namedQuery, int searchTerm) {
        Query q = session.createNamedQuery(namedQuery);
        q.setParameter("id", searchTerm);
        return (Race) q.getSingleResult();
    }

    public List<Race> findByName(String searchTerm) {
        Query query = session.createNamedQuery(Race.FIND_RACE_BY_NAME);
        query.setParameter("name", searchTerm);
        return query.getResultList();
    }

    public int findMaxStartNumber(int raceId) {
        Query query = session.createNamedQuery(Race.FIND_MAX_START_NUMBER);
        query.setParameter("raceId", raceId);
        return query.getFirstResult();
    }

    public void update(Race race) {
        session.update(race);
        getTransaction().commit();
        exit(false);
    }

    public void delete(Race race) {
        session.delete(race);
    }
}
