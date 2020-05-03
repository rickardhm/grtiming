package se.rihi.tidtagninig.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "FindRaceEventById", query = "from RaceEvent where id = :id"),
        @NamedQuery(name = "FindRaceByRaceEventId", query = "from Race where race_event_id =:race_event_id"),
        @NamedQuery(name = "FindRaceEventByName", query = "from RaceEvent where lower(name) like :name")
})

/**
 * Describes a single race
 * A Race has many to one relationship with RaceEvent
 * to find all races belonging to a RaceEvent:
 * select * from race_event inner join race on  race_event.id = race_event_id where race_event.id = 87;
 */
@Entity
@Table(name = "race_event")
public class RaceEvent implements Serializable {

    public static final String FIND_RACE_EVENT_BY_ID = "FindRaceEventById";
    public static final String FIND_RACE_BY_RACE_EVENT_ID = "FindRaceByRaceEventId";
    public static final String FIND_RACE_EVENT_BY_NAME = "FindRaceEventByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany
    @JoinColumn(name = "race_event_id")
    private List<Race> raceList = new ArrayList<>();
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "fee")
    private String fee;
    @Column(name = "date")
    private Date date;
    @Column(name = "event_location")
    private String eventLocation;

    public RaceEvent() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Race> getRaceList() {
        return raceList;
    }

    public void addRace(Race race) {
        getRaceList().add(race);
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

}
