package se.rich.grtiming.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NamedQueries({
        @NamedQuery(name = "FindFinishListById", query = "from FinishList where id = :id"),
        @NamedQuery(name = "FindFinishListByPosition", query = "from FinishList where race_id = :race_id and position = :position"),
        @NamedQuery(name = "FindFinishListByRaceId", query = "from FinishList where race_id = :race_id order by position"),
        @NamedQuery(name = "FindFinishByStartNumber", query = "from FinishList where start_number = :start_number"),
        @NamedQuery(name = "GetMaxPosition", query = "select MAX(position) from FinishList where race_id = :race_id"),
        @NamedQuery(name = "FindFinishListByName", query = "from FinishList where lower(name) like :name")
})

@Entity
@Table(name = "finishList")
public class FinishList implements Serializable {

    public static final String FIND_FINISH_LIST_BY_ID = "FindFinishListById";
    public static final String FIND_FINISH_LIST_BY_POSITION = "FindFinishListByPosition";
    public static final String FIND_FINISH_LIST_BY_RACE_ID = "FindFinishListByRaceId";
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
    @Column(name = "start_number")
    private int startNumber;
    @Column(name = "position")
    private int position;
    @Column(name = "race_id")
    private int race_id;

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

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
