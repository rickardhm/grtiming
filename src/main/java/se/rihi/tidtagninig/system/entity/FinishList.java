package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NamedQueries({
        @NamedQuery(name = "FindFinishListById", query = "from FinishList where id = :id"),
        @NamedQuery(name = "FindFinishByStartNumber", query = "from FinishList where nr = :nr"),
        @NamedQuery(name = "GetMaxPosition", query = "select MAX(position) from FinishList where race_id = :race_id"),
        @NamedQuery(name = "FindFinishListByName", query = "from FinishList where lower(name) like :name")
})

@Entity
@Table(name = "finishList")
public class FinishList implements Serializable {

    public static final String FIND_FINISH_LIST_BY_ID = "FindFinishListById";
    public static final String FIND_FINISH_BY_START_NUMBER = "FindFinishByStartNumber";
    public static final String GET_MAX_POSITION = "GetMaxPosition";
    public static final String FIND_FINISH_LIST_BY_NAME = "FindFinishListByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "finishTime")
    private Date finishTime;
    @Column(name = "finishString")
    private String finishString;
    @Column(name = "nr")
    private int nr;
    @Column(name = "position")
    private int position;
    @ManyToOne(fetch = FetchType.LAZY)
    private Race race;

    public FinishList() {}

    public FinishList(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getFinishString() {
        return finishString;
    }

    public void setFinishString(String finishString) {
        this.finishString = finishString;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
