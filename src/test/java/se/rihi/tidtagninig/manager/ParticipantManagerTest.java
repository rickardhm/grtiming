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
    public void testCreate() {
        Participant p = new Participant();
        p.setFirstName("Erik");
        p.setLastName("Svensson");
        p.setEmail("erik@foo.com");
        p.setPhone("90512");
        p.setClub("Stockholm");
        p.setAge(44);
        p.setSex("male");
        manager.create(p);
    }

    @Test
    public void testRead() {
        List<Participant> list = manager.read();
        for (Participant p: list) {
            System.out.println(p.getFirstName() + " " + p.getLastName());
        }
        assertTrue(list.size() > 0);
    }

    @Test
    public void testFindById() {
        Participant p = manager.findById(Participant.FIND_USER_BY_ID, 6);
        System.out.println("p " + p.getFirstName() + " " + p.getLastName());
    }

    @Test
    public void testFindByName() {
        List<Participant> list = manager.findByName(Participant.FIND_USER_BY_NAME, "sv%");
        for (Participant p: list) {
            System.out.println("p " + p.getFirstName() + " " + p.getLastName());
        }
    }

    @Test
    public void testUpdate() {
        Participant p = manager.findById(Participant.FIND_USER_BY_ID, 6);
        System.out.println("before " + p.getAge());
        p.setAge(47);
        manager.update(p);
        Participant p2 = manager.findById(Participant.FIND_USER_BY_ID, 6);
        System.out.println("after " + p2.getAge());
    }

    public void testDelete() {
    }
}