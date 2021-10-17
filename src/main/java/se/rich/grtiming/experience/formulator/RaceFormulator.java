package se.rich.grtiming.experience.formulator;

import se.rich.grtiming.system.entity.Race;
import se.rich.grtiming.system.manager.RaceManager;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class RaceFormulator {
    private RaceManager manager = new RaceManager();
    private List headers = new ArrayList();

    private String name;
    private String description;
    private String raceDate;
    private String startTime;
    private String fee;
    private String distance;

    public Object[] getHeaders() {
        headers.add("id");
        headers.add("Name");
        headers.add("Description");
        headers.add("RaceDate");
        headers.add("StartTime");
        headers.add("Fee");
        headers.add("Distance");
        return headers.toArray();
    }

    public void setHeaders(List headers) {
        this.headers = headers;
    }

    public DefaultTableModel addRows(DefaultTableModel model) {
        List<Race> raceList = manager.read();
        for (Race r: raceList){
            List list = new ArrayList();
            list.add(r.getId());
            list.add(r.getName());
            list.add(r.getDescription());
            list.add(r.getRaceDate().toString());
            list.add(r.getStartTime().toString());
            list.add(r.getFee());
            list.add(r.getDistance());
            model.addRow(list.toArray());
        }
        return model;
    }

    public DefaultTableModel addRows(DefaultTableModel model, int id) {
        Race race = manager.findById(Race.FIND_RACE_BY_ID, id);
        List list = new ArrayList();
        list.add(race.getId());
        list.add(race.getName());
        list.add(race.getDescription());
        list.add(race.getRaceDate().toString());
        list.add(race.getStartTime().toString());
        list.add(race.getFee());
        list.add(race.getDistance());
        model.addRow(list.toArray());
        return model;
    }

}
