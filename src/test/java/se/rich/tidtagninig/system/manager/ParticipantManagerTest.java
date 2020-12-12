package se.rich.tidtagninig.system.manager;

import junit.framework.TestCase;
import se.rich.grtiming.system.manager.ParticipantManager;

public class ParticipantManagerTest extends TestCase {

    ParticipantManager manager;

    public void setUp() throws Exception {
        super.setUp();
        manager = new ParticipantManager();
    }

    public void testManager() {
        assertNotNull(manager);
    }

    public void testRead() {
    }

    public void testFindById() {
    }

    public void testFindByName() {
    }

}