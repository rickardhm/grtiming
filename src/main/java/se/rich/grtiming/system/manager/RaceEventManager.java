package se.rich.grtiming.system.manager;

import se.rich.grtiming.system.manager.interfaces.Manager;
import se.rich.grtiming.system.entity.Race;
import se.rich.grtiming.system.entity.RaceEvent;

import javax.persistence.Query;
import java.util.List;

public class RaceEventManager extends Manager {

    public RaceEventManager() {
        setup();
    }

    public void create(RaceEvent raceEvent) {
        session.persist(raceEvent);
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
        Query query = session.createNamedQuery(RaceEvent.FIND_RACE_BY_RACE_ID);
        query.setParameter("race_id", raceEventId);
        List<Race> list = query.getResultList();
        return list;
    }

    public void update(RaceEvent raceEvent) {
        session.update(raceEvent);
        getTransaction().commit();
    }

    public void delete(RaceEvent raceEvent) {
        session.delete(raceEvent);
        getTransaction().commit();
    }
}
