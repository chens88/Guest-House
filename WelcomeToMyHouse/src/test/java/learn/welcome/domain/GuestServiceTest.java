package learn.welcome.domain;

import learn.welcome.data.GuestFileRepository;
import learn.welcome.data.GuestRepositoryDouble;
import learn.welcome.models.Guest;
import learn.welcome.models.State;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

GuestService service = new GuestService (new GuestRepositoryDouble());



    //guest_id,first_name,last_name,email,phone,state
    //1,Sullivan,Lomas,slomas0@mediafire.com,(702) 7768761,NV
    @Test
    void shouldFindByEmail() {
        Guest guest = service.findByEmail("sshinobi@email.com");
        assertNotNull(guest);
        assertEquals(10, guest.getGuestId());
        assertEquals("Sai", guest.getFirstName());
        assertEquals("Shinobi", guest.getLastName());
        assertEquals("(917) 0000000", guest.getPhone());
        assertEquals(State.NY, guest.getState());
    }

    @Test
    void shouldNotFindUnknownEmail() {
        Guest guest = service.findByEmail("unknownemail@domain.com");
        assertNull(guest); //guest shouldn't exist
    }
}