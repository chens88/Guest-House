package learn.welcome.data;

import learn.welcome.models.Guest;

import javax.xml.crypto.Data;
import java.util.List;

public interface GuestRepository {

        //find all guests
        List<Guest> findAllGuests();
        Guest findByEmail(String email);

        Guest findById(int id);
}
