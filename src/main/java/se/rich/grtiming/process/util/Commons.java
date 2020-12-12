package se.rich.grtiming.process.util;

import se.rich.grtiming.system.entity.FinishList;
import se.rich.grtiming.system.entity.Participant;
import se.rich.grtiming.system.entity.Race;
import se.rich.grtiming.system.manager.FinishListManager;
import se.rich.grtiming.system.manager.ParticipantManager;
import se.rich.grtiming.system.manager.RaceManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Commons {
    public void registerFinish(Race race) {
        RaceManager raceManager = new RaceManager();
        addFinish(race);
        raceManager.exit(false);
    }

    public void addFinish(Race race) {
        FinishListManager listManager = new FinishListManager();
        FinishList finish = new FinishList();
        int nr;
        Object o = listManager.getMaxPosition(race.getId());
        if (null == o) {
            nr = 1;
        } else {
            nr = (int) o + 1;
        }
        finish.setPosition(nr);
        finish.setFinishTime(new Date());
        listManager.updateFinish(finish);
        listManager.getTransaction().commit();
        listManager.exit(false);
    }

    public void addFinisher(Race race, Participant participant, int pos) {
        FinishListManager manager = new FinishListManager();
    }

    /**
     * Calculate the difference between two dates
     * @param start
     * @param finish
     * @return A formatted string "## days ## hours ## minutes ##,### seconds"
     */
    public String displayFinishTime(Date start, Date finish) {
        Map<String, Long> hej = calculateTime(start, finish);
        String result = String.format("%s hours %s minutes %s,%s seconds",
                hej.get("hours"), hej.get("minutes"), hej.get("seconds"), hej.get("milliSeconds"));
        return result;
    }

    /**
     * Calculate the difference between two dates
     * @param start
     * @param finish
     * @return calculated time put in a HashMap (
     */
    public Map<String, Long> calculateTime(Date start, Date finish) {
        Map<String, Long> hej = new HashMap<>();
        long diff = finish.getTime() - start.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        hej.put("days", days);
        long hours = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
        hej.put("hours", hours);
        long minutes = diff / (60 * 1000) % 60;
        hej.put("minutes", minutes);
        long seconds = diff / 1000 % 60;
        hej.put("seconds", seconds);
        long milliSeconds = diff % 1000;
        hej.put("milliSeconds", milliSeconds);
        String result = String.format("%s days %s hours %s minutes %s,%s seconds", days, hours, minutes, seconds, milliSeconds);
        return hej;
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

    private void printHeadlines(String str) {
        String[] anmald = str.split(",");
        int i = 0;
        for (String person: anmald) {
            System.out.println("str[" + (i++) + "] -> " + person);
        }
    }
}
