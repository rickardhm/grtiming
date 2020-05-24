package se.rihi.tidtagninig.process.util;

import se.rihi.tidtagninig.system.entity.FinishList;
import se.rihi.tidtagninig.system.entity.Participant;
import se.rihi.tidtagninig.system.entity.Race;
import se.rihi.tidtagninig.system.manager.FinishListManager;
import se.rihi.tidtagninig.system.manager.RaceManager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Commons {
    public void registerFinish(int raceId) {
        RaceManager raceManager = new RaceManager();
        Race race = raceManager.findById(raceId);
        addFinish(race);
        raceManager.exit(false);
    }

    public void addFinish(Race race) {
        FinishListManager listManager = new FinishListManager();
        FinishList finish = new FinishList();
        int nr;
        Object o = listManager.getMaxPosition(finish.getRace().getId());
        if (null == o) {
            nr = 1;
        } else {
            nr = (int) o + 1;
        }
        finish.setPosition(nr);
        finish.setRace(race);
        finish.setFinishTime(new Date());
        listManager.updateFinish(finish);
        listManager.getTransaction().commit();
    }

    public void addFinisher(FinishList finishList, Participant participant) {
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
        String result = String.format("%s days %s hours %s minutes %s,%s seconds",
                hej.get("days"), hej.get("hours"), hej.get("minutes"), hej.get("seconds"), hej.get("milliSeconds"));
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
        hej.put("day", days);
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
}
