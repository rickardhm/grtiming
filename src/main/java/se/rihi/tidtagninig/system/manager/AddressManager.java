package se.rihi.tidtagninig.system.manager;

import se.rihi.tidtagninig.system.entity.Address;
import se.rihi.tidtagninig.system.entity.FinishList;
import se.rihi.tidtagninig.system.manager.interfaces.Manager;

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
