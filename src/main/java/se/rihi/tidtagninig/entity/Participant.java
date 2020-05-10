package se.rihi.tidtagninig.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "FindUserById", query = "from Participant where id = :id"),
        @NamedQuery(name = "FindUserByName", query = "from Participant where lower(name) like :name")
})


@Entity
@Table(name = "participant")
public class Participant implements Serializable {

    public static final String FIND_USER_BY_ID = "FindUserById";
    public static final String FIND_USER_BY_NAME = "FindUserByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    private Race race;
    @Column(name = "name")
    private String name;
    @Column(name = "club")
    private String club;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    private String sex;

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
        address.setParticipant(this);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
