package se.rich.tidtagninig.system.manager;

import junit.framework.TestCase;
import se.rich.grtiming.system.manager.RaceManager;

public class RaceManagerTest extends TestCase {

    RaceManager raceManager;

    public void setUp() throws Exception {
        super.setUp();
        raceManager = new RaceManager();
    }

    public void testManager() {
        assertNotNull(raceManager);
    }

    public void testRead() {
    }

    public void testFindById() {
    }

    public void testFindByName() {
    }

}