package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "FindRaceById", query = "from Race where id = :id"),
        //@NamedQuery(name = "FinMaxStartNumber", query = "select MAX(startNumber) from Participant where race.id = :raceId"),
        @NamedQuery(name = "FindRaceByName", query = "from Race where lower(name) like :name")
})

@Entity
@Table(name = "race")
public class Race implements Serializable {

    public static final String FIND_RACE_BY_ID = "FindRaceById";
    public static final String FIND_MAX_START_NUMBER = "FinMaxStartNumber";
    public static final String FIND_RACE_BY_NAME = "FindRaceByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinishList> finishList = new ArrayList<>();
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "raceDate")
    private Date raceDate;
    @Column(name = "startTime")
    private Date startTime;
    @Column(name = "fee")
    private String fee;
    @Column(name = "distance")
    private String distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
        //participant.setRace(this);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
        //participant.setRace(null);
    }

    public List<FinishList> getFinishList() {
        return finishList;
    }

    public void setFinishList(List<FinishList> finishList) {
        this.finishList = finishList;
    }

    public void addFinish(FinishList finish) {
        this.finishList.add(finish);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
