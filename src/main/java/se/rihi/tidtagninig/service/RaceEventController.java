package se.rihi.tidtagninig.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.rihi.tidtagninig.system.entity.Race;
import se.rihi.tidtagninig.system.entity.RaceEvent;
import se.rihi.tidtagninig.system.manager.RaceEventManager;

import java.util.List;

@RestController
public class RaceEventController {

    @GetMapping("/raceevent")
    List<RaceEvent> all() {
        RaceEventManager manager = new RaceEventManager();
        return manager.read();
    }

    @GetMapping(path = "/raceevent/{id}")
    public RaceEvent getPost(@PathVariable int id) {
        RaceEventManager manager = new RaceEventManager();
        RaceEvent raceEvent = (RaceEvent) manager.findById(Race.FIND_RACE_BY_ID, id);

        return raceEvent;
    }
}
