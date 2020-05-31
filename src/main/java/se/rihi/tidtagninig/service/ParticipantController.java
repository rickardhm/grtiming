package se.rihi.tidtagninig.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.rihi.tidtagninig.system.entity.Participant;
import se.rihi.tidtagninig.system.manager.ParticipantManager;

import java.util.List;

@RestController
public class ParticipantController {

    @GetMapping("/participants")
    List<Participant> all() {
        ParticipantManager manager = new ParticipantManager();
        return manager.read();
    }
}
