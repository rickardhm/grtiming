package se.rihi.tidtagninig.manager;

import junit.framework.TestCase;
import org.junit.Test;
import se.rihi.tidtagninig.entity.Participant;

import java.util.List;

public class ParticipantManagerTest extends TestCase {

    ParticipantManager manager;

    public void setUp() throws Exception {
        super.setUp();
        manager = new ParticipantManager();
    }

    @Test
    public void testRead() {
        List<Participant> list = manager.read();
        for (Participant p: list) {
            System.out.println(p.getName());
        }
        assertTrue(list.size() > 0);
    }

    @Test
    public void testFindById() {
        Participant p = manager.findById(Participant.FIND_USER_BY_ID, 6);
        System.out.println("p " + p.getName());
    }

    @Test
    public void testFindByName() {
        List<Participant> list = manager.findByName(Participant.FIND_USER_BY_NAME, "sv%");
        for (Participant p: list) {
            System.out.println("p " + p.getName());
        }
    }

}