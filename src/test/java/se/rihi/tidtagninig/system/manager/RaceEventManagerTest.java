package se.rihi.tidtagninig.system.manager;

import junit.framework.TestCase;
import org.junit.Test;
import se.rihi.tidtagninig.system.entity.FinishList;
import se.rihi.tidtagninig.system.entity.Race;
import se.rihi.tidtagninig.system.entity.RaceEvent;

import java.util.List;
import java.util.Random;

public class RaceEventManagerTest extends TestCase {

    RaceEventManager raceEventManager;

    public void setUp() throws Exception {
        super.setUp();
        raceEventManager = new RaceEventManager();
    }

    @Test
    public void testRead() {
        List<RaceEvent> list = raceEventManager.read();
        for (RaceEvent raceEvent: list) {
            System.out.println("event: " + raceEvent.getId() + " " + raceEvent.getName());
        }
    }

    public void testFindById() {
        RaceEvent raceEvent = raceEventManager.findById(RaceEvent.FIND_RACE_EVENT_BY_ID, 7719);
        System.out.println("Tävling: " + raceEvent.getName());
        List<Race> races = raceEvent.getRaceList();
        for (Race race: races) {
            System.out.println("  race: " + " " + race.getName() + " " + race.getDistance() + " " + race.getFee());
        }
    }

    public void testFindByName() {
    }

    @Test
    public void testFindByRaceEventId() {
        List<Race> list = raceEventManager.findByRaceEventId(87);
        for (Race race: list) {
            System.out.println("-> " + race.getName());
        }
    }

    private Race createRace(String name, String description, String distance) {
        Race race = new Race();
        FinishListManager finishListManager = new FinishListManager();
        List<FinishList> finishLists = finishListManager.read();
        int nr = new Random().nextInt(99);
        race.setName(name + "_" + nr);
        race.setDescription(description);
        race.setDistance(distance);
        return race;
    }
}