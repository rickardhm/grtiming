package se.rihi.tidtagninig.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.rihi.tidtagninig.system.entity.Post;
import se.rihi.tidtagninig.system.manager.PostManager;

import java.util.List;

@RestController
public class PostController {

    @GetMapping("/post")
    List<Post> all() {
        PostManager manager = new PostManager();
        return manager.read();
    }

    @GetMapping(path = "/post/{id}")
    public Post getPost(@PathVariable int id) {
        PostManager manager = new PostManager();
        Post post = (Post) manager.findById(Post.FIND_POST_BY_ID, id);
        return post;
    }
}
