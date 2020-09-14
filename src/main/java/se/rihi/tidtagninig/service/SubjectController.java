package se.rihi.tidtagninig.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.rihi.tidtagninig.system.entity.Subject;
import se.rihi.tidtagninig.system.manager.SubjectManager;

import java.util.List;

@RestController
public class SubjectController {

    @GetMapping("/subjects")
    List<Subject> all() {
        SubjectManager manager = new SubjectManager();
        return manager.read();
    }

    @GetMapping(path = "/subject/{id}")
    public Subject getUser(@PathVariable int id) {
        SubjectManager manager = new SubjectManager();
        Subject subject = (Subject) manager.findById(Subject.FIND_SUBJECT_BY_ID, id);
        return subject;
    }
}
