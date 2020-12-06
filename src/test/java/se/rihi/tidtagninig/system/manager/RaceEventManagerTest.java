package se.rihi.tidtagninig.system.manager;

import junit.framework.TestCase;
import org.junit.Test;

public class RaceEventManagerTest extends TestCase {

    RaceEventManager raceEventManager;

    public void setUp() throws Exception {
        super.setUp();
        raceEventManager = new RaceEventManager();
    }

    public void testManager() {
        assertNotNull(raceEventManager);
    }

    @Test
    public void testRead() {
    }

    public void testFindById() {
    }

    public void testFindByName() {
    }

    @Test
    public void testFindByRaceEventId() {
    }

}