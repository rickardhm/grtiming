package se.rich.grtiming.experience.formulator;

import se.rich.grtiming.system.entity.Participant;
import se.rich.grtiming.system.manager.ParticipantManager;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ParticapantFormulator {

    ParticipantManager participantManager = new ParticipantManager();
    private List headers = new ArrayList();

    public Object[] getHeaders() {
        headers.add("id");
        headers.add("Name");
        headers.add("Registation date");
        headers.add("Club");
        headers.add("Age");
        headers.add("Start number");
        return headers.toArray();
    }

    public ParticapantFormulator() {
    }


    public DefaultTableModel addRows(DefaultTableModel model, int id) {
        List<Participant> participants = participantManager.findByRaceId(id);
        for (Participant p : participants) {
            List l = new ArrayList();
            l.add(p.getId());
            l.add(p.getName());
            l.add(p.getRegDate());
            l.add(p.getClub());
            l.add(p.getAge());
            l.add(p.getStartNumber());
            model.addRow(l.toArray());
        }
        return model;
    }

    public DefaultTableModel addRows(DefaultTableModel model) {
        List<Participant> participants = participantManager.read();
        for (Participant p : participants) {
            List l = new ArrayList();
            l.add(p.getId());
            l.add(p.getName());
            l.add(p.getRegDate());
            l.add(p.getClub());
            l.add(p.getAge());
            l.add(p.getStartNumber());
            model.addRow(l.toArray());
        }
        return model;
    }
}