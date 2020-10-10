package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;

@Entity(name = "PostComment")
public class PostComment {

    public PostComment() {}
    public PostComment(String review) {
        this.review = review;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComment )) return false;
        return id != null && id.equals(((PostComment) o).getId());
    }
}
