package se.rihi.tidtagninig.manager;

import se.rihi.tidtagninig.entity.Race;
import se.rihi.tidtagninig.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class RaceManager extends Manager {

    public RaceManager() {
        setup();
    }

    public void create(Race race) {
        if (!session.isOpen()) {
            setup();
        }
        session.persist(race);
    }

    public List<Race> read() {
        if (!session.isOpen()) {
            setup();
        }
        Query q = session.createQuery("from Race");
        List<Race> list = q.getResultList();
        return list;
    }

    public Race findById(int raceId) {
        Query q = session.createNamedQuery(Race.FIND_RACE_BY_ID);
        q.setParameter("id", raceId);
        Race race = (Race) q.getSingleResult();
        return race;
    }

    public List<Race> findByName(String searchTerm) {
        Query query = session.createNamedQuery(Race.FIND_RACE_BY_NAME);
        query.setParameter("name", searchTerm);
        List<Race> list = query.getResultList();
        return list;
    }

    public int findMaxStartNumber(int raceId) {
        Query query = session.createNamedQuery(Race.FIND_MAX_START_NUMBER);
        query.setParameter("raceId", raceId);
        int max = query.getFirstResult();
        return max;
    }

    public void update(Race race) {
        session.update(race);
    }

    public void delete(Race race) {
        session.delete(race);
    }
}
