package se.rihi.tidtagninig.system.manager;

import se.rihi.tidtagninig.system.entity.Post;
import se.rihi.tidtagninig.system.manager.interfaces.Manager;

import javax.persistence.Query;
import java.util.List;

public class PostManager extends Manager {

    public PostManager() { setup();}

    public void create(Post post) {
        session.persist(post);
    }

    public List<Post> read() {
        Query q = session.createQuery("from Post");
        List<Post> list = q.getResultList();
        return list;
    }

    public Post findById(String namerQuery, int searchTerm) {
        Query q = session.createNamedQuery(namerQuery);
        q.setParameter("id", searchTerm);
        Post post = (Post) q.getSingleResult();
        return post;
    }

}
