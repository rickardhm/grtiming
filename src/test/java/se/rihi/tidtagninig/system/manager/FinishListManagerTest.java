package se.rihi.tidtagninig.system.manager;

import junit.framework.TestCase;
import se.rihi.tidtagninig.system.entity.FinishList;

import java.util.List;

public class FinishListManagerTest extends TestCase {

    FinishListManager manager;

    public void setUp() throws Exception {
        super.setUp();
        manager = new FinishListManager();
    }

    public void testCreate() {
        FinishList finishList = new FinishList();
        ParticipantManager participantManager = new ParticipantManager();
        participantManager.read();
    }

    public void testUpdate() {
        FinishList finishList = manager.findByPossition(FinishList.FIND_FINISH_LIST_BY_POSITION, 8084, 1);
        System.out.println("nr: " + finishList.getStartNumber());
        finishList.setStartNumber(3);
        manager.updateFinish(finishList);
        System.out.println("nr: " + finishList.getStartNumber());
    }

    public void testFindByRaceId() {
        List<FinishList> finishList = manager.findByRaceId(FinishList.FIND_FINISH_LIST_BY_RACE_ID, 8084);
        System.out.println(finishList.size());
    }

    public void testRead() {
        List<FinishList> finishLists = manager.read();
        for (FinishList list: finishLists) {
            System.out.println(list.getId());
        }
    }

    public void testFindById() {
    }

    public void testFindByParticipant() {
    }

    public void testDelete() {
    }
}