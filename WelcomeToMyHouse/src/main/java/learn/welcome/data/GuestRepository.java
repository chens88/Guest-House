package learn.welcome.data;

import learn.welcome.models.Guest;

import javax.xml.crypto.Data;
import java.util.List;

public interface GuestRepository {
        Guest findGuestById (String guestId);
        List<Guest> findAllGuests();
        List <Guest> findByEmail (String email);
        boolean update(Guest guest) throws DataException;
}
