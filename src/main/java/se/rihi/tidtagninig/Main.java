package se.rihi.tidtagninig;

import se.rihi.tidtagninig.entity.Participant;
import se.rihi.tidtagninig.manager.ParticipantManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run1();
        //main.readParticipants(false);
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
        manager.exit();
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
                    participant.setEmail(anmald[1]);
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
            manager.exit();
        }
    }

    private void printHeadlines(String str) {
        String[] anmald = str.split(",");
        int i = 0;
        for (String person: anmald) {
            System.out.println("str[" + (i++) + "] -> " + person);
        }
    }
}
