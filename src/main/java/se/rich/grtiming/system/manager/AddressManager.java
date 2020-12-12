package se.rich.grtiming.system.manager;

import se.rich.grtiming.system.manager.interfaces.Manager;
import se.rich.grtiming.system.entity.Address;

import javax.persistence.Query;
import java.util.List;

public class AddressManager extends Manager {

    public AddressManager() {
        setup();
    }

    public List<Address> read() {
        Query q = session.createQuery("from Address");
        List<Address> list = q.getResultList();
        return list;
    }

    public Address findById(String namerQuery, int searchTerm) {
        Query q = session.createNamedQuery(namerQuery);
        q.setParameter("id", searchTerm);
        Address address = (Address) q.getSingleResult();
        return address;
    }
}
