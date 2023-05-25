package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;

import java.util.List;

public interface ReservationRepository {

        List<Reservation> findByHost(Host host);
//        List<Reservation> findByHostAndGuest(Host host, Guest guest);

        Reservation addReservation(Reservation reservation) throws DataException;
        boolean editReservation(Reservation reservation) throws DataException;
        boolean cancelReservation(Reservation reservation) throws DataException;


}