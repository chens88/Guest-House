package learn.welcome.data;

import learn.welcome.models.Reservation;

import java.util.List;

public interface ReservationRepository {

        List<Reservation> findAllReservations(String string) throws DataException;
        List<Reservation> findByHost(String string) throws DataException;
        Reservation addReservation(Reservation reservation) throws DataException;
        Reservation editReservation(Reservation reservation) throws DataException;
        Reservation cancelReservation(String reservationId) throws DataException;
        boolean update(Reservation reservation) throws DataException;



}