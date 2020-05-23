package se.rihi.tidtagninig.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "FindFinishListById", query = "from FinishList where id = :id"),
        @NamedQuery(name = "GetMaxPosition", query = "select MAX(position) from FinishList where race_id = :raceId"),
        @NamedQuery(name = "FindFinishListByName", query = "from FinishList where lower(name) like :name")
})

@Entity
@Table(name = "finishList")
public class FinishList implements Serializable {

    public static final String FIND_FINISH_LIST_BY_ID = "FindFinishListById";
    public static final String GET_MAX_POSITION = "GetMaxPosition";
    public static final String FIND_FINISH_LIST_BY_NAME = "FindFinishListByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Race race;
    @Column(name = "finishTime")
    private Date finishTime;
    @Column(name = "nr")
    private int nr;
    @Column(name = "position")
    private int position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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
