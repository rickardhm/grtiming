package se.rihi.tidtagninig.system.manager;

import se.rihi.tidtagninig.system.entity.RaceEvent;
import se.rihi.tidtagninig.system.entity.FinishList;
import se.rihi.tidtagninig.system.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class FinishListManager extends Manager {

    public FinishListManager() {
        setup();
    }

    public void create(FinishList finishList) {
        session.persist(finishList);
    }

    public List<FinishList> read() {
        Query q = session.createQuery("from FinishList");
        List<FinishList> list = q.getResultList();
        return list;
    }

    public RaceEvent findById(String namerQuery, int searchTerm) {
        Query query = session.createNamedQuery(RaceEvent.FIND_RACE_EVENT_BY_ID);
        query.setParameter("id", searchTerm);
        RaceEvent startList = (RaceEvent) query.getSingleResult();
        return startList;
    }

    public Object getMaxPosition(int raceId) {
        Query query = session.createNamedQuery(FinishList.GET_MAX_POSITION);
        query.setParameter("raceId", raceId);
        Object pos = query.getSingleResult();
        return pos;
    }

    /**
     * Will add finish time and position to this finishList
     * @param finish the finishList to update
     */
    public void updateFinish(FinishList finish) {
        session.persist(finish);
    }

    public List<FinishList> findByParticipant(String namedQuery, String searchTerm) {
        return null;
    }

    public void delete(FinishList finishList) {
        session.delete(finishList);
        getTransaction().commit();
    }
}
