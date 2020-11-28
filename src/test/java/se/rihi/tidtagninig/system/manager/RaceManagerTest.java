package se.rihi.tidtagninig.system.manager;

import junit.framework.TestCase;
import se.rihi.tidtagninig.system.entity.Race;

import java.util.List;

public class RaceManagerTest extends TestCase {

    RaceManager raceManager;

    public void setUp() throws Exception {
        super.setUp();
        raceManager = new RaceManager();
    }

    public void testRead() {
        List<Race> list = raceManager.read();
        for (Race race: list) {
            System.out.println("-> " + race.getName());
        }
        assertTrue(list.size() > 0);
    }

    public void testFindById() {
    }

    public void testFindByName() {
    }

}