package se.rihi.tidtagninig.system.manager;

import se.rihi.tidtagninig.system.entity.Race;
import se.rihi.tidtagninig.system.manager.interfaces.Manager;

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

    public Race findById(int raceId) {
        Query q = session.createNamedQuery(Race.FIND_RACE_BY_ID);
        q.setParameter("id", raceId);
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
    }

    public void delete(Race race) {
        session.delete(race);
    }
}
