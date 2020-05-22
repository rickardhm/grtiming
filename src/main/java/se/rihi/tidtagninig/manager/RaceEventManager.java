package se.rihi.tidtagninig.manager;

import se.rihi.tidtagninig.entity.Race;
import se.rihi.tidtagninig.entity.RaceEvent;
import se.rihi.tidtagninig.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class RaceEventManager extends Manager {

    public RaceEventManager() {
        setup();
    }

    public RaceEvent create(RaceEvent raceEvent) {
        session.persist(raceEvent);
        return raceEvent;
    }

    public List<RaceEvent> read() {
        Query q = session.createQuery("from RaceEvent");
        List<RaceEvent> list = q.getResultList();
        return list;
    }

    public RaceEvent findById(String namerQuery, int searchTerm) {
        Query query = session.createNamedQuery(RaceEvent.FIND_RACE_EVENT_BY_ID);
        query.setParameter("id", searchTerm);
        RaceEvent raceEvent = (RaceEvent) query.getSingleResult();
        return raceEvent;
    }

    public List<RaceEvent> findByName(String namedQuery, String searchTerm) {
        return null;
    }

    public List<Race> findByRaceEventId(int raceEventId) {
        Query query = session.createNamedQuery(RaceEvent.FIND_RACE_BY_RACE_EVENT_ID);
        query.setParameter("race_event_id", raceEventId);
        List<Race> list = query.getResultList();
        return list;
    }

    public void update(RaceEvent raceEvent) {

    }

    public void delete() {

    }
}
