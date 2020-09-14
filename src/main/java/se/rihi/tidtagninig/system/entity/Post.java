package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "FindPostById", query = "from Post where id = :id")
})

@Entity
@Table(name = "post")
public class Post implements Serializable, Comparable<Post> {

    public static final String FIND_POST_BY_ID = "FindPostById";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public void addComment(PostComment comment) {
        this.comments.add(comment);
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + this.getComments().toString();
    }

    @Override
    public int compareTo(Post post) {
        return 0;
    }
}
