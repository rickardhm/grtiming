package se.rihi.tidtagninig.system.manager;

import junit.framework.TestCase;
import org.junit.Test;
import se.rihi.tidtagninig.system.entity.Participant;

import java.util.List;

public class ParticipantManagerTest extends TestCase {

    ParticipantManager manager;

    public void setUp() throws Exception {
        super.setUp();
        manager = new ParticipantManager();
    }

    public void testRead() {
        List<Participant> list = manager.read();
        for (Participant p: list) {
            System.out.print(p.getId() + " " );
            System.out.print(p.getName() + " ");
            if (null != p.getAddress()) {
                System.out.print(p.getAddress().getEmail());
            }
            System.out.println();
        }
        assertTrue(list.size() > 0);
    }

    public void testFindById() {
        Participant p = manager.findById(Participant.FIND_USER_BY_ID, 7748);
        System.out.println("p " + p.getName() + " " + p.getClub() + " " + p.getAddress().getEmail());
    }

    public void testFindByName() {
        List<Participant> list = manager.findByName(Participant.FIND_USER_BY_NAME, "a%");
        for (Participant p: list) {
            System.out.println("p " + p.getName());
        }
    }

}