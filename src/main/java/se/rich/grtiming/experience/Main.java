package se.rich.grtiming.experience;

import org.apache.log4j.Logger;
import se.rich.grtiming.process.util.Commons;
import se.rich.grtiming.system.entity.FinishList;
import se.rich.grtiming.system.entity.Participant;
import se.rich.grtiming.system.entity.Race;
import se.rich.grtiming.system.entity.RaceEvent;
import se.rich.grtiming.system.manager.ParticipantManager;
import se.rich.grtiming.system.manager.RaceEventManager;
import se.rich.grtiming.system.manager.RaceManager;
import se.rich.grtiming.system.manager.FinishListManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main implements ActionListener {

    final static Logger logger = Logger.getLogger(Main.class);
    private static Commons commons = new Commons();
    private Maker maker = new Maker();
    int counter = 0;
    JLabel label;
    JTextArea textArea;

    public Main() {
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        JPanel panel = new JPanel();

        JButton button = new JButton("Click me");
        button.addActionListener(this);
        label = new JLabel("Number of clicks: 0");
        textArea = new JTextArea();
        textArea.setSize(400,400);

        JScrollPane scrollH = new JScrollPane (textArea);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);
        panel.add(scrollH);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hello");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
        //main.testCreateRace(16637);
        //int id = main.functionTest();
        //int id = 16977;
        //main.tearDown(id);
        //main.findSomething();
    }

    private void testCreateRace(int race_event_id) {
        RaceEventManager eventManager = new RaceEventManager();
        RaceEvent raceEvent = eventManager.findById(RaceEvent.FIND_RACE_EVENT_BY_ID, race_event_id);
        if (null != raceEvent) {
            Race race = new Race();
            race.setName("Empty");
            raceEvent.addRace(race);
            eventManager.update(raceEvent);
        } else {
            logger.warn("race event with id: " + race_event_id + " not found");
        }
    }

    /**
     * This function test will create a RaceEvent
     *
     * @return the ID of the RaceEvent
     */
    public int functionTest() {
        //Cretes an RaceEvent
        int eventId = maker.makeRaceEvent("The Awesome Winter Run");
        logger.info("Creating RaceEvent: " + eventId);

        //Fetch the Race from that RaceEvent
        RaceEventManager eventManager = new RaceEventManager();
        RaceEvent raceEvent = eventManager.findById(RaceEvent.FIND_RACE_BY_RACE_ID, eventId);
        FinishListManager finishListManager;
        logger.info("Event: " + raceEvent.getName());
        int nr = 0;
        for (Race race: raceEvent.getRaceList()) {
            logger.info("Race[" + nr + "](" + race.getId() + "): " + race.toString());
            //Update the starTime of that race
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -120);
            race.setStartTime(cal.getTime());
            raceEvent.getRaceList().set(nr++, race);
            eventManager.update(raceEvent);
            //eventManager.exit(false);
            //Adds a finish to the Race
            for (int i = 1; i < 4; i++) {
                finishListManager = new FinishListManager();
                finishListManager.registerFinish(race.getId());
                FinishList finishList = finishListManager.findByPossition(FinishList.FIND_FINISH_LIST_BY_POSITION, race.getId(), i);
                finishList.setStartNumber((3 * i));
                finishListManager.updateFinish(finishList);
                finishListManager.exit(false);
                try {
                    Thread.sleep(1350);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Commons commons = new Commons();
            }
            int i = 1;
            ParticipantManager participantManager = new ParticipantManager();
            logger.info("The result is:");
            for (FinishList finishList1: race.getFinishList()) {
                if (null != finishList1.getFinishTime()) {
                    Participant participant = participantManager.findByStartNumber(race.getId(), finishList1.getStartNumber());
                    logger.info("#" + i + ": startNumber " + finishList1.getStartNumber() + ", " + participant.toString() + ", tid: " + commons.displayFinishTime(race.getStartTime(), finishList1.getFinishTime()));
                } else {
                    logger.info("No finish time on pos#" + i);
                }
                i++;
            }
        }
        return eventId;
    }

    private void tearDown(int id) {
        logger.info("Deleting RaceEvent: " + id);
        RaceEventManager eventManager = new RaceEventManager();
        RaceEvent raceEvent = eventManager.findById(RaceEvent.FIND_RACE_EVENT_BY_ID, id);
        eventManager.delete(raceEvent);
    }

    private void findSomething() {
        ParticipantManager participantManager = new ParticipantManager();
        Participant participant = participantManager.findByStartNumber(11982, 3);
        //List<Participant> participant = participantManager.findByStartNumber(11982);
        logger.info(participant.getName());
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
                List<FinishList> finish = finishLists.stream().filter(p -> p.getStartNumber() == participant.getStartNumber()).collect(Collectors.toList());
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        counter++;
        label.setText("Number of clicks: " + counter);
        if (counter > 1) {
            readParticipants();
        }
    }

    private void readParticipants() {
        ParticipantManager manager = new ParticipantManager();
        List<Participant> list = manager.read();
        for (Participant participant: list) {
            textArea.append(participant.getName() + "\n");
        }
    }
}
