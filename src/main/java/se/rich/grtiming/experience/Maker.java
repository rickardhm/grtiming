package se.rich.grtiming.experience;

import org.apache.log4j.Logger;
import se.rich.grtiming.system.entity.*;
import se.rich.grtiming.system.manager.ParticipantManager;
import se.rich.grtiming.system.manager.RaceEventManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Maker {

    final static Logger logger = Logger.getLogger(Maker.class);

    /**
     * Creates a RaceEvent with three races and a number of Participants attached to each Race
     */
    public int makeRaceEvent(String name) {
        RaceEventManager raceEventManager = new RaceEventManager();
        RaceEvent raceEvent = new RaceEvent();
        raceEvent.setDescription("Description of the event");
        raceEvent.setEventLocation("By the lake");
        raceEvent.setDate(new Date());
        raceEvent.setName(name);
        raceEvent.addRace(makeRace("Trail", (int) getRndName(new Object[]{10, 20, 40, 80}), 10));
        raceEvent.addRace(makeRace("Flat", (int) getRndName(new Object[]{10, 20, 40, 80}), 50));
        raceEvent.addRace(makeRace("Mountain", (int) getRndName(new Object[]{10, 20, 40, 80}), 100));
        raceEventManager.create(raceEvent);
        raceEventManager.exit(false);
        return raceEvent.getId();
    }

    public Race makeRace(String name, int distance, int numberOfPartcipants) {
        Race race = new Race();
        race.setRaceDate(new Date());
        //race.addFinish(makeFinishLIst(race));
        race.setName(name);
        race.setDistance(distance + "km");
        race.setDescription("Description of the race");
        race.setFee((distance * 10 + ""));
        for (int i=0; i < numberOfPartcipants; i++) {
            race.addParticipant(makeParticipant(race, (i + 1)));
        }
        return race;
    }

    public FinishList makeFinishLIst(Race race) {
        FinishList finishList = new FinishList(null);
        return finishList;
    }

    public Participant makeParticipant(Race race, int startNumber) {
        Participant participant = new Participant();
        participant.setName(makeName());
        participant.setAge((int) getRndName(new Object[]{28, 34, 46, 55, 52, 38, 26}));
        participant.addAddress(makeAdress(participant.getName()));
        participant.setClub(makeClub());
        participant.setRegDate(new Date());
        //participant.setRace(race);
        participant.setStartNumber(startNumber);
        return participant;
    }

    public Address makeAdress(String name) {
        Address address = new Address();
        address.setEmail(makeEmail(name));
        address.setPhone("90510");
        return address;
    }

    public String makeEmail(String name) {
        String mail = name.replace(" ", ".") + "@foo.com";
        return mail.toLowerCase();
    }

    private String makeName() {
        Random random = new Random();
        Object[] firstNames = null;
        Object[] lastNames = null;
        try {
            Stream stream1 = Files.lines(Paths.get("src/main/resources/names.txt"));
            firstNames = stream1.toArray();
            Stream stream2 = Files.lines(Paths.get("src/main/resources/last_names.txt"));
            lastNames = stream2.toArray();
        } catch (IOException e) {
            logger.warn(e.getLocalizedMessage());
        }
        if (null == firstNames) {
            logger.info("Generating names from a list");
            firstNames = new Object[]{"james", "Ian", "Emely", "Kurt", "Loise", "Robert", "Ben", "Lotta", "Nisse"};
        }
        if (null == lastNames) {
            logger.info("Generating lastNames from a list");
            lastNames = new Object[]{"Forsberg", "Lane", "Lind", "Ohlsson", "Flemming", "Bond", "Hur", "Svensson", "Hult"};
        }
        String cFirstName = (String) firstNames[random.nextInt(firstNames.length)];
        String cLastName = (String) lastNames[random.nextInt(lastNames.length)];
        cLastName = cLastName.substring(0,1).toUpperCase() + cLastName.substring(1).toLowerCase();
        String name = cFirstName + " " + cLastName;

        return name;
    }

    public Object getRndName(Object[] objects) {
        Random random = new Random();
        return objects[random.nextInt(objects.length)];
    }

    public String makeClub() {
        String[] club = {"Norway", "Denmark", "Island", "Soumi", "Sweden"};
        Random random = new Random();
        return club[random.nextInt(club.length)];
    }

    public void generateStartNumber(Race race, int startPoint) {
        ParticipantManager pm = new ParticipantManager();
        List<Participant> list = race.getParticipants();
        Collections.sort(list);
        int nr = startPoint;
        for (Participant participant: list) {
            nr++;
            participant.setStartNumber(nr);
            pm.update(participant);
        }
        pm.exit(false);
    }

}
