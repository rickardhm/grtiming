package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "FindSubjectById", query = "from Subject where id = :id"),
        @NamedQuery(name = "FindSubjectByName", query = "from Subject where lower(name) like :name")
})

@Entity
@Table(name = "subject")
public class Subject implements Serializable, Comparable<Subject> {

    public static final String FIND_SUBJECT_BY_ID = "FindSubjectById";
    public static final String FIND_SUBJECT_BY_NAME = "FindSubjectByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
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
        buffer.append(";");
        buffer.append(this.name);
        return buffer.toString();
    }

    @Override
    public int compareTo(Subject o) {
        return 0;
    }

}
