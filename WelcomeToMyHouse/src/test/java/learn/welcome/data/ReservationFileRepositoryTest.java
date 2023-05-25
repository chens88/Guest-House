package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ReservationFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/test-data/reservation-test/reservation-seed.csv";
    static final String TEST_FILE_PATH = "./data/test-data/reservation-test/3edda6bc-ab95-49a8-8962-d50b53f84b15.csv";
    static final String TEST_DIR_PATH = "./data/test-data/reservation-test";
    static final int RESERVATION_COUNT = 12;
    final String hostId = "3edda6bc-ab95-49a8-8962-d50b53f84b15";
    final Host host = new Host();

    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setUpTest() throws IOException {
        host.setHostId(hostId);
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    //view reservation by host - id 3edda6bc-ab95-49a8-8962-d50b53f84b15.csv
//id,start_date,end_date,guest_id,total
//1,2021-07-31,2021-08-07,640,2550
    @Test
    void shouldFindByHost() throws DataException {
        List<Reservation> reservations = repository.findByHost(host);
        assertEquals(RESERVATION_COUNT, reservations.size());

    }
//    id,start_date,end_date,guest_id,total
    @Test
    void shouldAddReservation() throws DataException{
        Guest guest = new Guest();
        guest.setGuestId(6006);

        Reservation newReservation = new Reservation(13, LocalDate.now(), LocalDate.now().plusDays(5), host, guest, new BigDecimal(900));
        Reservation actual = repository.addReservation(newReservation);
        assertNotNull(actual);
        assertEquals(13, actual.getId());
        assertEquals(hostId, actual.getHost().getHostId());
        assertEquals(newReservation.getTotal(), actual.getTotal());

    }
//1,2021-07-31,2021-08-07,640,2550
    @Test
    void shouldEditReservation() throws DataException{
        Guest guest = new Guest();
        guest.setGuestId(799);

        Reservation newReservation = new Reservation(5, LocalDate.now(), LocalDate.now().plusDays(5), host, guest, new BigDecimal(900));
        assertTrue(repository.editReservation(newReservation));
    }

    @Test
    void shouldNotEditNonExistingReservation() throws DataException{
        Guest guest = new Guest();
        guest.setGuestId(799);

        Reservation newReservation = new Reservation(1999, LocalDate.now(), LocalDate.now().plusDays(5), host, guest, new BigDecimal(900));
        assertFalse(repository.editReservation(newReservation));
    }

    @Test
    void shouldCancelReservation() throws DataException {
        Guest guest = new Guest();
        guest.setGuestId(799);

        Reservation newReservation = new Reservation(5, LocalDate.now(), LocalDate.now().plusDays(5), host, guest, new BigDecimal(900));
        assertTrue(repository.cancelReservation(newReservation));
    }

    @Test
    void shouldNotCancelReservation() throws DataException{
        Guest guest = new Guest();
        guest.setGuestId(799);

        Reservation newReservation = new Reservation(1000, LocalDate.now(), LocalDate.now().plusDays(5), host, guest, new BigDecimal(900));
        assertFalse(repository.cancelReservation(newReservation));
    }
}