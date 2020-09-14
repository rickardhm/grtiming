package se.rihi.tidtagninig.system.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "FindAddressById", query = "from Address where id = :id"),
        @NamedQuery(name = "FindAddressByName", query = "from Address where lower(name) like :name")
})

@Entity
@Table(name = "address")
public class Address {

    public static final String FIND_ADDRESS_BY_ID = "FindAddressById";
    public static final String FIND_ADDRESS_BY_NAME = "FindAddressByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }*/

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

    @Override
    public String toString() {
        return "id: " + getId() + ", email: " + getEmail() + " phone: " + getPhone();
    }
}
