package se.rihi.tidtagninig;

import se.rihi.tidtagninig.entity.Participant;
import se.rihi.tidtagninig.manager.ParticipantManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParticipantManager manager = new ParticipantManager();
        manager.setup();
        //manager.create();
        //manager.read();
        Participant p = manager.findById(Participant.FIND_USER_BY_ID,5);
        if (null != p) {
            System.out.println(p.getFirstName() + " " + p.getLastName());
        }
        List<Participant> list = manager.findByName(Participant.FIND_USER_BY_NAME, "do%");
        for (Participant participant: list) {
            System.out.println("p: " + participant.getFirstName() + " " + participant.getLastName());
        }
        manager.exit();
    }

    public void readParticipants() {
        String filename = "participants.csv";
    }
}
