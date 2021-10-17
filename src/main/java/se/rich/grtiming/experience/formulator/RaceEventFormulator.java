package se.rich.grtiming.experience.formulator;

import se.rich.grtiming.system.entity.RaceEvent;
import se.rich.grtiming.system.manager.RaceEventManager;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class RaceEventFormulator {
    private RaceEventManager raceEventManager = new RaceEventManager();
    private List headers = new ArrayList();

    public Object[] getHeaders() {
        headers.add("id");
        headers.add("Name");
        headers.add("Race list");
        headers.add("Description");
        headers.add("RaceDate");
        headers.add("eventLocation");
        return headers.toArray();
    }

    public DefaultTableModel addRows(DefaultTableModel model) {
        List<RaceEvent> raceEvents = raceEventManager.read();
        for (RaceEvent raceEvent: raceEvents) {
            List list = new ArrayList();
            list.add(raceEvent.getId());
            list.add(raceEvent.getName());
            model.addRow(list.toArray());
        }

        return model;
    }
}
