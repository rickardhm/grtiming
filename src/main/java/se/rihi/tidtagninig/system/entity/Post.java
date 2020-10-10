package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Post")
public class Post {

    public Post() {}

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "post_id")
    private List<PostComment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        comments.add(comment);
        //comment.setPost(this);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
        //comment.setPost(null);
    }

}
