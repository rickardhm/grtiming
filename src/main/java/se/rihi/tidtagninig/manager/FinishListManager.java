package se.rihi.tidtagninig.manager;

import se.rihi.tidtagninig.entity.RaceEvent;
import se.rihi.tidtagninig.entity.FinishList;
import se.rihi.tidtagninig.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.Date;
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

    public Object getMax(int raceId) {
        Query query = session.createNamedQuery(FinishList.GET_MAX_POSITION);
        query.setParameter("raceId", raceId);
        Object pos = query.getSingleResult();
        return pos;
    }

    /**
     * Will add finish time and position to this finishList
     * @param finish the finishList to update
     */
    public void addFinish(FinishList finish) {
        int nr;
        Object o = getMax(finish.getRace().getId());
        if (null == o) {
            nr = 1;
        } else {
            nr = (int) o + 1;
        }
        finish.setPosition(nr);
        finish.setFinishTime(new Date());
        session.persist(finish);
        getTransaction().commit();
    }

    public List<FinishList> findByParticipant(String namedQuery, String searchTerm) {
        return null;
    }

    public void update(FinishList finishList) {
        session.update(finishList);
        getTransaction().commit();
    }

    public void delete(FinishList finishList) {
        session.delete(finishList);
        getTransaction().commit();
    }
}
