package se.rihi.tidtagninig.manager;

import se.rihi.tidtagninig.entity.RaceEvent;
import se.rihi.tidtagninig.entity.FinishList;
import se.rihi.tidtagninig.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class FinishListManager extends Manager {

    public FinishListManager() {
        setup();
    }

    public FinishList create(FinishList finishList) {
        session.persist(finishList);
        return finishList;
    }

    public List<FinishList> read() {
        Query q = session.createQuery("from RaceEvent");
        List<FinishList> list = q.getResultList();
        return list;
    }

    public RaceEvent findById(String namerQuery, int searchTerm) {
        Query query = session.createNamedQuery(RaceEvent.FIND_RACE_EVENT_BY_ID);
        query.setParameter("id", searchTerm);
        RaceEvent startList = (RaceEvent) query.getSingleResult();
        return startList;
    }

    public List<FinishList> findByParticipant(String namedQuery, String searchTerm) {
        return null;
    }

    public void update(FinishList finishList) {

    }

    public void delete() {

    }
}
