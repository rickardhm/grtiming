package se.rihi.tidtagninig.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.rihi.tidtagninig.system.entity.Address;
import se.rihi.tidtagninig.system.manager.AddressManager;

import java.util.List;

@RestController
public class AdressController {

    @GetMapping("/address")
    List<Address> all() {
        AddressManager manager = new AddressManager();
        return manager.read();
    }

    @GetMapping(path = "/address/{id}")
    public Address getAddress(@PathVariable int id) {
        AddressManager manager = new AddressManager();
        Address address = (Address) manager.findById(Address.FIND_ADDRESS_BY_ID, id);
        return address;
    }

}
