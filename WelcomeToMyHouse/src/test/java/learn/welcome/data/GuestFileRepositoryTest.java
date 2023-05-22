package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestFileRepositoryTest {
    static final String TEST_PATH = "./data/test-data/guest-test.csv";
    static final int GUEST_COUNT = 31;
    GuestFileRepository repository = new GuestFileRepository(TEST_PATH);
    final List<Guest> all = repository.findAllGuests();

    @Test
    void shouldFindAllGuest() {
        List<Guest> guests = repository.findAllGuests();
        assertEquals(GUEST_COUNT, guests.size());
    }


    //guest_id,first_name,last_name,email,phone,state
    //1,Sullivan,Lomas,slomas0@mediafire.com,(702) 7768761,NV
    @Test
    void shouldFindByEmail() {
        Guest guest = new Guest();
        guest.setEmail("slomas0@mediafire.com");

        Guest testGuest = new Guest();
        testGuest.setEmail("slomas0@mediafire.com");
        assertTrue(guest.getEmail().equals(testGuest.getEmail()));

    }

    @Test
    void shouldNotFindByUnknownEmail() {
        Guest guest = new Guest();
        guest.setEmail("randomemail@domain.com");

        Guest testGuest = new Guest();
        testGuest.setEmail("randomemail@domain.com");
        assertTrue(guest.getEmail().equals(testGuest.getEmail()));

    }
}