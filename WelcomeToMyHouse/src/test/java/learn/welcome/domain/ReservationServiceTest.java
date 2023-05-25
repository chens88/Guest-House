package learn.welcome.domain;

import learn.welcome.data.DataException;
import learn.welcome.data.GuestRepositoryDouble;
import learn.welcome.data.HostRepositoryDouble;
import learn.welcome.data.ReservationRepositoryDouble;
import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import learn.welcome.models.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ReservationServiceTest {
    private ReservationService reservationService;

    @BeforeEach
    public void setup() {
        reservationService = new ReservationService(new ReservationRepositoryDouble(), new HostRepositoryDouble(), new GuestRepositoryDouble());
    }

    @Test
    void findByHost() {
        Host host1 = new Host("13","Kakashi", "sensei@email.com", "(516) 0000000", State.NY, "123 Konaha Lane", "Leaf Village", "11111", new BigDecimal(800), new BigDecimal(600));
        Guest guest1 = new Guest(10,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        List<Reservation> reservations = reservationService.findByHost(host1);
        assertEquals(reservations.size(),1);

        Reservation kakashisres = reservations.get(0);
        assertEquals(100, kakashisres.getId());
        assertEquals(LocalDate.of(2022, 9,1), kakashisres.getStart());
        assertEquals(LocalDate.of(2022, 10, 1), kakashisres.getEnd());
        assertEquals(host1, kakashisres.getHost());
        assertEquals(guest1, kakashisres.getGuest());
        assertEquals(new BigDecimal(5000), kakashisres.getTotal());
    }

    @Test
    void shouldNotFindByHost() {
        Host host1 = new Host("11","Jiraya", "j@email.com", "(516) 0000000", State.NY, "123 Konaha Lane", "Leaf Village", "11111", new BigDecimal(800), new BigDecimal(600));
        List<Reservation> reservations = reservationService.findByHost(host1);
        assertEquals(reservations.size(),0);

    }

    @Test
    void findByHostAndGuest() {
        Host host2 = new Host("17","Haku", "haku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Guest guest2 = new Guest(11, "Naruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        List<Reservation> reservations = reservationService.findByHostAndGuest(host2, guest2);

        //Reservation(300, LocalDate.of(2023, 1,13), LocalDate.of(2023, 3,20), host2, guest2, new BigDecimal(8000));
        Reservation reservation2 = reservations.get(0);
        assertEquals(300, reservation2.getId());
        assertEquals(LocalDate.of(2023, 1, 13), reservation2.getStart());
        assertEquals(LocalDate.of(2023, 3, 20), reservation2.getEnd());
        assertEquals(host2, reservation2.getHost());
        assertEquals(guest2, reservation2.getGuest());
        assertEquals(new BigDecimal(8000), reservation2.getTotal());

    }

    @Test
    void shouldNotFindByHostAndGuest() {
        Host host2 = new Host("19","Baku", "baku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Guest guest2 = new Guest(11, "Boruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        List<Reservation> reservations = reservationService.findByHostAndGuest(host2, guest2);

        //Reservation(300, LocalDate.of(2023, 1,13), LocalDate.of(2023, 3,20), host2, guest2, new BigDecimal(8000));
        assertEquals(reservations.size(), 0);
    }

    @Test
    void cancelReservation() throws DataException {
        Host host3 = new Host("30","Tsunade", "tsunade@hokage.com", "(555) 0000000", State.CO, "1 Hokage Road", "Leaf Village", "39273", new BigDecimal(700), new BigDecimal(1000));
        Guest guest3 = new Guest(55, "Choji", "Butterfly", "chojibutterfly@email.com", "(111) 2223333", State.TX);
        Reservation reservation3 = new Reservation(800, LocalDate.of(2025, 7,9), LocalDate.of(2025, 9,20), host3, guest3, new BigDecimal(15000));

        Result<Reservation> result = reservationService.cancelReservation(reservation3);
        assertTrue(result.isSuccess());
        assertEquals(result.getPayload(), reservation3);

    }

    @Test
    void shouldNotCancelReservation() throws DataException {
        Host host2 = new Host("17","Haku", "haku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Guest guest2 = new Guest(11, "Naruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        Reservation reservation2 = new Reservation(300, LocalDate.of(2023, 1,13), LocalDate.of(2023, 3,20), host2, guest2, new BigDecimal(8000));
        Result<Reservation> result = reservationService.cancelReservation(reservation2);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void editReservation() throws DataException {
        Host host2 = new Host("17","Haku", "haku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Guest guest2 = new Guest(11, "Naruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        Reservation reservation2 = new Reservation(300, LocalDate.of(2025, 2,13), LocalDate.of(2025, 8,20), host2, guest2, new BigDecimal(30000));
        Result<Reservation> result = reservationService.editReservation(reservation2);
        assertTrue(result.isSuccess());
        assertEquals(reservation2, result.getPayload());
    }

    @Test
    void shouldNotEditReservation() throws DataException {
        Host host2 = new Host("17","Haku", "haku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Guest guest2 = new Guest(11, "Naruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        Reservation reservation2 = new Reservation(300, LocalDate.of(2020, 2,13), LocalDate.of(2020, 8,20), host2, guest2, new BigDecimal(30000));
        Result<Reservation> result = reservationService.editReservation(reservation2);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void addReservation() throws DataException {
        Host host3 = new Host("30","Tsunade", "tsunade@hokage.com", "(555) 0000000", State.CO, "1 Hokage Road", "Leaf Village", "39273", new BigDecimal(700), new BigDecimal(1000));
        Guest guest1 = new Guest(10,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        Reservation reservation2 = new Reservation(1000, LocalDate.of(2023, 12,1), LocalDate.of(2023, 12,12), host3, guest1, new BigDecimal(5000));
        Result<Reservation> result = reservationService.addReservation(reservation2);
        assertTrue(result.isSuccess());
        assertEquals(reservation2, result.getPayload());

    }

    @Test
    void shouldNotAddReservation() throws DataException{
        Host host3 = new Host("33","Tsunade", "nottsunade@hokage.com", "(555) 0000000", State.CO, "1 Hokage Road", "Leaf Village", "39273", new BigDecimal(700), new BigDecimal(1000));
        Guest guest1 = new Guest(10,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        Reservation reservation2 = new Reservation(1000, LocalDate.of(2023, 12,1), LocalDate.of(2023, 12,12), host3, guest1, new BigDecimal(5000));
        Result<Reservation> result = reservationService.addReservation(reservation2);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldValidate() {
        Host host1 = new Host("11","Jiraya", "j@email.com", "(516) 0000000", State.NY, "123 Konaha Lane", "Leaf Village", "11111", new BigDecimal(800), new BigDecimal(600));
        Guest guest1 = new Guest(10,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        Reservation reservation2 = new Reservation(1000, LocalDate.of(2023, 12,1), LocalDate.of(2023, 12,12), host1, guest1, new BigDecimal(5000));
        Result<Reservation> result = reservationService.validate(reservation2);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotValidate() {
        Host host1 = new Host("13","Jiraya", "j@email.com", "(516) 0000000", State.NY, "123 Konaha Lane", "Leaf Village", "11111", new BigDecimal(800), new BigDecimal(600));
        Guest guest1 = new Guest(67,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        Reservation reservation2 = new Reservation(1000, LocalDate.of(2023, 12,1), LocalDate.of(2023, 12,12), host1, guest1, new BigDecimal(5000));
        Result<Reservation> result = reservationService.validate(reservation2);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

}
