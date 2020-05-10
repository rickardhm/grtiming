package se.rihi.tidtagninig.manager;

import junit.framework.TestCase;
import se.rihi.tidtagninig.entity.FinishList;

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

    public void testUpdate() {
    }

    public void testDelete() {
    }
}