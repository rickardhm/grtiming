package se.rihi.tidtagninig.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "FindRaceById", query = "from Race where id = :id"),
        @NamedQuery(name = "FindRaceByName", query = "from Race where lower(name) like :name")
})

@Entity
@Table(name = "race")
public class Race implements Serializable {

    public static final String FIND_RACE_BY_ID = "FindRaceById";
    public static final String FIND_RACE_BY_NAME = "FindRaceByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
