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

    public Race findById(String namerQuery, int searchTerm) {
        Query q = session.createNamedQuery(namerQuery);
        q.setParameter("id", searchTerm);
        Race race = (Race) q.getSingleResult();
        return race;
    }

    public List<Race> findByName(String namedQuery, String searchTerm) {
        return null;
    }

    public void update(Race race) {

    }

    public void delete() {

    }
}
