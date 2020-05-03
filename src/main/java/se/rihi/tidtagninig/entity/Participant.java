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
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
