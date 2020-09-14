package se.rihi.tidtagninig.experience;

import se.rihi.tidtagninig.system.entity.*;
import se.rihi.tidtagninig.system.manager.ParticipantManager;
import se.rihi.tidtagninig.system.manager.PostManager;
import se.rihi.tidtagninig.system.manager.RaceEventManager;
import se.rihi.tidtagninig.system.manager.SubjectManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Maker {

    /**
     * Creates a RaceEvent with three races and a number of Participants attached to each Race
     */
    public int makeRaeEvent() {
        RaceEventManager raceEventManager = new RaceEventManager();
        RaceEvent raceEvent = new RaceEvent();
        raceEvent.setDescription("Beskrivning av eventet");
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

    public Race makeRace(String name, int distance, int numberOfPartcipants) {
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

    public FinishList makeFinishLIst(Race race) {
        FinishList finishList = new FinishList(null);
        return finishList;
    }

    public Participant makeParticipant(Race race, int startNumber) {
        Participant participant = new Participant();
        participant.setName(maketName());
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
        String[] club = {"SLDK", "stockholm gerillalöpare", "Team skavsåret", "Linnea", "Sweden Runners"};
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

    public void makeSubject() {
        SubjectManager manager = new SubjectManager();
        Subject subject = new Subject();
        subject.setName(maketName());
        subject.setAge(52);
        subject.setAddress(makeAdress(subject.getName()));
        subject.setClub(makeClub());
        subject.setRegDate(new Date());
        subject.setStartNumber(0);
        manager.create(subject);
        manager.exit(false);
    }

    public void makePost() {
        PostManager postManager = new PostManager();
        Post post = new Post();
        post.setTitle("First post");
        PostComment comment1 = new PostComment();
        comment1.setReview("First comment");
        PostComment comment2 = new PostComment();
        comment2.setReview("Second comment");
        post.addComment(comment2);
        postManager.create(post);
        postManager.exit(false);
    }

}
