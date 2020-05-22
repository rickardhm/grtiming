package se.rihi.tidtagninig;

import se.rihi.tidtagninig.entity.Address;
import se.rihi.tidtagninig.entity.Participant;
import se.rihi.tidtagninig.entity.Race;
import se.rihi.tidtagninig.entity.RaceEvent;
import se.rihi.tidtagninig.manager.ParticipantManager;
import se.rihi.tidtagninig.manager.RaceEventManager;
import se.rihi.tidtagninig.manager.RaceManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        //System.out.println(main.getMax(300));
        int eventId = main.makeRaeEvent();
        RaceManager raceManager = new RaceManager();
        Race race = raceManager.findById(425);
        main.generateStartNumber(race, 100);
        //main.displayRace(403);
        //main.readParticipants(false);
    }

    private int getMax(int raceId) {
        ParticipantManager manager = new ParticipantManager();
        Object p = manager.getMaxStartNumber(raceId);
        int nr = 0;
        if (null != p) {
            if (p instanceof Integer) {
                nr = (int) p;
            }
        }
        return nr;
    }

    private void run1() {
        ParticipantManager manager = new ParticipantManager();
        manager.setup();
        //manager.create();
        //manager.read();
        Participant p = manager.findById(Participant.FIND_USER_BY_ID,10);
        if (null != p) {
            System.out.println(p.getName());
        }
        List<Participant> list = manager.findByName(Participant.FIND_USER_BY_NAME, "%ny%");
        for (Participant participant: list) {
            System.out.println("p: " + participant.getId() + " " + participant.getName());
        }
        manager.exit(true);
    }

    public void readParticipants(boolean toDB) {
        String fileName = "participants.csv";
        List<String> list = new ArrayList();
        ParticipantManager manager = null;

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {


            list = stream
                    .filter(line -> !line.startsWith("line3"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        int nr = 0;
        for (String str: list) {
            if (nr == 0) {
                System.out.println(str);
                printHeadlines(str);
            } else {
                String[] anmald = str.split(",");
                if (!anmald[0].isBlank()) {
                    Participant participant = new Participant();
                    participant.setName(anmald[2]);
                    participant.setClub(anmald[3]);
                    participant.setSex(anmald[9]);
                    if (toDB) {
                        if (null == manager) {
                            manager = new ParticipantManager();
                        }
                        manager.create(participant);
                    }
                }
            }
            nr++;
        }
        if (toDB) {
            manager.exit(true);
        }
    }

    /**
     * Creates a RaceEvent with three races and three Participants attached to each Race
     */
    private int makeRaeEvent() {
        RaceEventManager raceEventManager = new RaceEventManager();
        RaceEvent raceEvent = new RaceEvent();
        raceEvent.setDescription("Skogsloppet");
        raceEvent.setEventLocation("vid sjön");
        raceEvent.setDate(new Date());
        raceEvent.setName("SommarLoppet");
        raceEvent.addRace(makeRace("femti", 50));
        raceEvent.addRace(makeRace("hundra", 100));
        raceEvent.addRace(makeRace("tvåhundra", 200));
        raceEventManager.create(raceEvent);
        raceEventManager.exit(false);
        return raceEvent.getId();
    }

    /**
     * Reads all Raceeents and display all its content
     */
    private void run5() {
        RaceEventManager manager = new RaceEventManager();
        List<RaceEvent> event = manager.read();
        for (RaceEvent raceEvent: event) {
            System.out.println(raceEvent.getName());
            System.out.println(raceEvent.getDate());
            System.out.println("Följande Tävlingar");
            for (Race list: raceEvent.getRaceList()) {
                System.out.println(" " + list.getName() + " - " + list.getDistance());
                for (Participant participant: list.getParticipants()) {
                    System.out.println("  " + participant.getName() + " " + participant.getStartNumber());
                }
            }
            System.out.println(" ");
        }

    }

    private void displayRace(int receId) {
        RaceEventManager eventManager = new RaceEventManager();
        for (Race race: eventManager.findByRaceEventId(receId)) {
            generateStartNumber(race, 100);
            System.out.println(race.getName() + " " + race.getDistance());
            List<Participant> list = race.getParticipants();
            Collections.sort(list);
            for (Participant participant: list) {
                System.out.print(" ");
                System.out.println(participant.getName() + " " + participant.getStartNumber() + " " + participant.getRegDate());
            }
        }
    }

    private void generateStartNumber(Race race, int startPoint) {
        ParticipantManager pm = new ParticipantManager();
        List<Participant> list = race.getParticipants();
        Collections.sort(list);
        for (Participant participant: list) {
            int max = getMax(participant.getRace().getId());
            int nr = 0;
            if (max < startPoint) {
                nr = startPoint + max + 1;
            } else {
                nr = max + 1;
            }
            participant.setStartNumber(nr);
            pm.update(participant);
        }
    }

    private Race makeRace(String name, int distance) {
        Race race = new Race();
        race.setRaceDate(new Date());
        race.setName(name);
        race.setDistance(distance + "km");
        race.setDescription("ett lopp");
        race.setFee((distance * 10 + ""));
        race.addParticipant(makeParticipant(race));
        race.addParticipant(makeParticipant(race));
        race.addParticipant(makeParticipant(race));
        return race;
    }

    private Participant makeParticipant(Race race) {
        Participant participant = new Participant();
        participant.setName(maketName());
        participant.addAddress(makeAdress(participant.getName()));
        participant.setClub(makeClub());
        participant.setRegDate(new Date());
        participant.setRace(race);
        participant.setStartNumber(0);
        return participant;
    }

    private Address makeAdress(String name) {
        Address address = new Address();
        address.setEmail(makeEmal(name));
        address.setPhone("90510");
        return address;
    }

    private String makeEmal(String name) {
        String mail = name.replace(" ", ".") + "@foo.com";
        return mail.toLowerCase();
    }

    private String maketName() {
        String[] sNames = {"james", "Ian", "Emely", "Kurt", "Loise", "Robert" };
        String[] lName = {"Forsberg", "Lane", "Lind", "Ohlsson", "Flemming", "Bond"};
        Random random = new Random();
        String name = sNames[random.nextInt(sNames.length)] + " " + lName[random.nextInt(lName.length)];
        return name;
    }

    private String makeClub() {
        String[] club = {"SLDK", "stockholm gerillalöpare", "Team skavsåret"};
        Random random = new Random();
        return club[random.nextInt(club.length)];
    }

    private void printHeadlines(String str) {
        String[] anmald = str.split(",");
        int i = 0;
        for (String person: anmald) {
            System.out.println("str[" + (i++) + "] -> " + person);
        }
    }

}
