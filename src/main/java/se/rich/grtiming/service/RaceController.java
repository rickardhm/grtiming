package se.rich.grtiming.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.rich.grtiming.system.entity.Race;
import se.rich.grtiming.system.manager.RaceManager;
import se.rich.grtiming.system.manager.FinishListManager;

import java.util.List;

@RestController
public class RaceController {

    @GetMapping("/race")
    List<Race> all() {
        RaceManager manager = new RaceManager();
        return manager.read();
    }

    @GetMapping(path = "/race/{id}")
    public Race getPost(@PathVariable int id) {
        RaceManager manager = new RaceManager();
        Race race = (Race) manager.findById(Race.FIND_RACE_BY_ID, id);

        return race;
    }

    @GetMapping(path="register/{id}")
    public void registerFinish(@PathVariable int id) {
        FinishListManager manager = new FinishListManager();
        manager.registerFinish(id);
    }

}
