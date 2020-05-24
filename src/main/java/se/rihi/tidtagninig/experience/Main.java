package se.rihi.tidtagninig.experience;

import se.rihi.tidtagninig.system.entity.*;
import se.rihi.tidtagninig.system.manager.FinishListManager;
import se.rihi.tidtagninig.system.manager.ParticipantManager;
import se.rihi.tidtagninig.system.manager.RaceEventManager;
import se.rihi.tidtagninig.process.util.Commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static Commons commons = new Commons();

    public static void main(String[] args) {
        Main main = new Main();
        //System.out.println(main.getMax(300));
        /*int eventId = main.makeRaeEvent();
        System.out.println(eventId);*/
        //commons.registerFinish(7720);
        //System.out.println(main.getMaxPos(4571));
        /*RaceManager raceManager = new RaceManager();
        Race race = raceManager.findById(639);
        main.generateStartNumber(race, 100);
        raceManager.exit(false);*/
        /*RaceManager raceManager = new RaceManager();
        Race r1 = raceManager.findById(447);
        int max = raceManager.findMaxStartNumber(r1.getId());
        Participant p1 = main.makeParticipant(r1, (max +1));
        r1.addParticipant(p1);
        raceManager.update(r1);
        raceManager.getTransaction().commit();*/
        main.displayRace(7720);
        //main.listSomeParticipants();
        //main.readParticipants(false);
    }

    private void listSomeParticipants() {
        Race race = new Race();
        List<Participant> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(makeParticipant(race, i));
        }
        for (Participant p: list) {
            System.out.println(p.getName() + " " + p.getClub());
        }
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
        manager.exit(false);
        return nr;
    }

    private int getMaxPos(int raceId) {
        FinishListManager manager = new FinishListManager();
        Object o = manager.getMaxPosition(raceId);
        return (int) o;
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
                    participant.setGender(anmald[9]);
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
        raceEvent.addRace(makeRace((String) getRndName(new Object[]{"Skogslöpet", "Snöracet", "Myrruset", "Bergsjoggen"}), (int) getRndName(new Object[]{10, 20, 40, 80}), 10));
        raceEvent.addRace(makeRace((String) getRndName(new Object[]{"Skogslöpet", "Snöracet", "Myrruset", "Bergsjoggen"}), (int) getRndName(new Object[]{10, 20, 40, 80}), 50));
        raceEvent.addRace(makeRace((String) getRndName(new Object[]{"Skogslöpet", "Snöracet", "Myrruset", "Bergsjoggen"}), (int) getRndName(new Object[]{10, 20, 40, 80}), 100));
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
            List<FinishList> finishLists = race.getFinishList();
            System.out.println(race.getName() + " " + race.getDistance());
            List<Participant> list = race.getParticipants();
            Collections.sort(list);
            for (Participant participant: list) {
                System.out.print(" ");
                System.out.print("Nr: " + participant.getStartNumber() + " name: " + participant.getName()  + " age: " + participant.getAge());
                List<FinishList> finish = finishLists.stream().filter(p -> p.getNr() == participant.getStartNumber()).collect(Collectors.toList());
                if (finish.size() > 0) {
                    Date start = race.getRaceDate();
                    Date fin = finish.get(0).getFinishTime();
                    long result = fin.getTime() - start.getTime();
                    System.out.print(" position: " + finish.get(0).getPosition() + " - " + commons.displayFinishTime(start, fin));
                }
                System.out.println();
            }
        }
    }

    private void generateStartNumber(Race race, int startPoint) {
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

    private Race makeRace(String name, int distance, int numberOfPartcipants) {
        Race race = new Race();
        race.setRaceDate(new Date());
        race.addFinish(makeFinishLIst(race));
        race.setName(name);
        race.setDistance(distance + "km");
        race.setDescription("Beskrivning av loppet");
        race.setFee((distance * 10 + ""));
        for (int i=0; i < numberOfPartcipants; i++) {
            race.addParticipant(makeParticipant(race, (i + 1)));
        }
        return race;
    }

    private FinishList makeFinishLIst(Race race) {
        FinishList finishList = new FinishList();
        return finishList;
    }

    private Participant makeParticipant(Race race, int startNumber) {
        Participant participant = new Participant();
        participant.setName(maketName());
        participant.setAge((int) getRndName(new Object[]{28, 34, 46, 55, 52, 38, 26}));
        participant.addAddress(makeAdress(participant.getName()));
        participant.setClub(makeClub());
        participant.setRegDate(new Date());
        participant.setRace(race);
        participant.setStartNumber(startNumber);
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
        Random random = new Random();
        Object[] firstNames = null;
        Object[] lastNames = null;
        try {
            Stream stream1 = Files.lines(Paths.get("/home/rickard/names.txt"));
            firstNames = stream1.toArray();
            Stream stream2 = Files.lines(Paths.get("/home/rickard/last_names.txt"));
            lastNames = stream2.toArray();
            //System.out.println(sList[random.nextInt(sList.length)]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == firstNames) {
            firstNames = new Object[]{"james", "Ian", "Emely", "Kurt", "Loise", "Robert", "Ben", "Lotta", "Nisse"};
        }
        if (null == lastNames) {
            lastNames = new Object[]{"Forsberg", "Lane", "Lind", "Ohlsson", "Flemming", "Bond", "Hur", "Svensson", "Hult"};
        }
        String name = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
        return name;
    }

    private Object getRndName(Object[] objects) {
        Random random = new Random();
        return objects[random.nextInt(objects.length)];
    }

    private String makeClub() {
        String[] club = {"SLDK", "stockholm gerillalöpare", "Team skavsåret", "Linnea"};
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
