package se.rihi.tidtagninig.manager;

import junit.framework.TestCase;
import org.junit.Test;
import se.rihi.tidtagninig.entity.Race;
import se.rihi.tidtagninig.entity.RaceEvent;
import se.rihi.tidtagninig.manager.interfaces.Manager;

import java.rmi.server.RMIClassLoader;
import java.time.LocalDate;
import java.util.*;

public class RaceEventManagerTest extends TestCase {

    EventManager eventManager;

    public void setUp() throws Exception {
        super.setUp();
        eventManager = new EventManager();
    }

    @Test
    public void testRead() {
        List<RaceEvent> list = eventManager.read();
        for (RaceEvent raceEvent: list) {
            System.out.println("-> " + raceEvent.getDescription());
        }
    }

    public void testFindById() {
    }

    public void testFindByName() {
    }

    @Test
    public void testFindByRaceEventId() {
        List<Race> list = eventManager.findByRaceEventId(RaceEvent.FIND_RACE_BY_RACE_EVENT_ID, 87);
        for (Race race: list) {
            System.out.println("-> " + race.getName());
        }
    }

    private Race createRace(String name, String description, String distance) {
        Race race = new Race();
        int nr = new Random().nextInt(99);
        race.setName(name + "_" + nr);
        race.setDescription(description);
        race.setDistance(distance);
        return race;
    }
}