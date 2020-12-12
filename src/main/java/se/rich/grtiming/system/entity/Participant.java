package se.rich.grtiming.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "FindParticipantById", query = "from Participant where id = :id"),
        @NamedQuery(name = "FindParticipantByStartNumber", query = "from Participant where race_id = :race_id and start_number = :start_number"),
        @NamedQuery(name = "MaxStartNumber", query = "select MAX(startNumber) from Participant where race_id = :raceId"),
        @NamedQuery(name = "FindParticipantByName", query = "from Participant where lower(name) like :name")
})


@Entity
@Table(name = "participant")
public class Participant implements Serializable, Comparable<Participant> {

    public static final String FIND_USER_BY_ID = "FindParticipantById";
    public static final String FIND_USER_BY_START_NUMBER = "FindParticipantByStartNumber";
    public static final String FIND_MAX_START_NUMBER = "MaxStartNumber";
    public static final String FIND_USER_BY_NAME = "FindParticipantByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    @Column(name = "regDate")
    private Date regDate;
    @Column(name = "name")
    private String name;
    @Column(name = "club")
    private String club;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "start_number")
    private int startNumber;

    public Participant() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addAddress(Address address) {
        this.address = address;
    }

    /*public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }*/

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.name);
        buffer.append(", ");
        buffer.append(this.club);
        return buffer.toString();
    }

    @Override
    public int compareTo(Participant o) {
        return getRegDate().compareTo(o.getRegDate());
    }
}
