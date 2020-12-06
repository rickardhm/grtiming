package se.rihi.tidtagninig.system.manager;

import se.rihi.tidtagninig.process.util.Commons;
import se.rihi.tidtagninig.system.entity.FinishList;
import se.rihi.tidtagninig.system.entity.Race;
import se.rihi.tidtagninig.system.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class FinishListManager extends Manager {

    public FinishListManager() {
        setup();
    }

    public void create(FinishList finishList) {
        session.persist(finishList);
        getTransaction().commit();
    }

    public List<FinishList> read() {
        Query q = session.createQuery("from FinishList");
        List<FinishList> list = q.getResultList();
        return list;
    }

    public FinishList findById(String namerQuery, int searchTerm) {
        Query query = session.createNamedQuery(namerQuery);
        query.setParameter("id", searchTerm);
        FinishList finishList = (FinishList) query.getSingleResult();
        return finishList;
    }

    public FinishList findByPossition(String namerQuery, int race_id, int position) {
        Query query = session.createNamedQuery(namerQuery);
        query.setParameter("race_id", race_id);
        query.setParameter("position", position);
        FinishList finishList = (FinishList) query.getSingleResult();
        return finishList;
    }

    public List<FinishList> findByRaceId(String namerQuery, int searchTerm) {
        Query query = session.createNamedQuery(namerQuery);
        query.setParameter("race_id", searchTerm);
        List<FinishList> finishList = (List<FinishList>) query.getResultList();
        return finishList;
    }

    /**
     * Return the maximun position from the finishList
     * @param raceId
     * @return
     */
    public Object getMaxPosition(int raceId) {
        Query query = session.createNamedQuery(FinishList.GET_MAX_POSITION);
        query.setParameter("race_id", raceId);
        Object pos = query.getSingleResult();
        return pos;
    }

    /**
     * Update the finishList
     * @param finish the finishList to update
     * @return
     */
    public boolean updateFinish(FinishList finish) {
        boolean returnValue = false;
        try {
            session.persist(finish);
            getTransaction().commit();
            returnValue = true;
        } catch (Exception e) {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Adds a finish to the finishlist with the finish time set
     * @param raceId The id witch this finish time should be register
     * @return
     */
    public FinishList registerFinish(int raceId) {
        Commons commons = new Commons();
        int pos = 0;
        RaceManager raceManager = new RaceManager();
        FinishListManager finishListManager = new FinishListManager();
        Race race = raceManager.findById(Race.FIND_RACE_BY_ID, raceId);
        FinishList finishList = new FinishList(new Date());
        Object fl = finishListManager.getMaxPosition(raceId);
        if (null != fl) {
            pos = (int) fl;
        }
        finishList.setFinishString(commons.displayFinishTime(race.getStartTime(), finishList.getFinishTime()));
        finishList.setPosition(++pos);
        race.addFinish(finishList);
        raceManager.update(race);
        return finishList;
    }

    public void startRace(int raceId) {
        RaceManager raceManager = new RaceManager();
        Race race = raceManager.findById(Race.FIND_RACE_BY_ID, raceId);
        race.setStartTime(new Date());
        raceManager.update(race);
    }

    public List<FinishList> findByParticipant(String namedQuery, String searchTerm) {
        return null;
    }

    public void delete(FinishList finishList) {
        session.delete(finishList);
        getTransaction().commit();
    }
}

