package se.rihi.tidtagninig.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.rihi.tidtagninig.system.entity.Post;
import se.rihi.tidtagninig.system.manager.PostManager;

import java.util.List;

@RestController
public class PostController {

    @GetMapping("/post")
    List<Post> all() {
        PostManager postManager = new PostManager();
        return postManager.read();
    }
}
