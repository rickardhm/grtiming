package se.rihi.tidtagninig.experience;

import org.postgresql.shaded.com.ongres.scram.common.exception.ScramServerErrorException;
import se.rihi.tidtagninig.process.util.Commons;
import se.rihi.tidtagninig.system.entity.Subject;
import se.rihi.tidtagninig.system.entity.*;
import se.rihi.tidtagninig.system.manager.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static Commons commons = new Commons();
    private Maker maker = new Maker();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        displayTime();
        //maker.makeSubject();
        //maker.makePost();
        //System.out.println(main.getMax(300));
        /*int eventId = maker.makeRaeEvent();
        System.out.println(eventId);*/
        //RaceManager raceManager = new RaceManager();
        //Race race = raceManager.findById(7720);
        //commons.registerFinish(race);
        //commons.addFinisher();
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
        //main.displayRace(7720);
        //main.displayPost(8151);
        //main.listSomeParticipants();
        //main.readParticipants(false);
    }

    public void displayTime() {
        RaceManager raceManager = new RaceManager();
        Race race = raceManager.findById(Race.FIND_RACE_BY_ID, 8106);

        FinishListManager finishListManager = new FinishListManager();
        FinishList finishList = finishListManager.findById(FinishList.FIND_FINISH_LIST_BY_ID, 8421);
        String result = commons.displayFinishTime(race.getStartTime(), finishList.getFinishTime());
        System.out.println(result);
    }

    private void listSomeParticipants() {
        Race race = new Race();
        List<Participant> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(maker.makeParticipant(race, i));
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

    private void displayPost(int postId) {
        PostManager postManager = new PostManager();
        List<Post> posts = postManager.read();
        for (Post p: posts) {
            System.out.println(p.getTitle());
            for (PostComment comment: p.getComments()) {
                System.out.println(comment.getReview());
            }
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

}
