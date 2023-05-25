package learn.welcome.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import learn.welcome.data.DataException;
import learn.welcome.data.GuestRepository;
import learn.welcome.data.HostRepository;
import learn.welcome.data.ReservationRepository;
import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository; //- pass in constructor
    private final HostRepository hostRepository; // - pass in constructor
    private final GuestRepository guestRepository; //- pass in constructor

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    // method signature for find by host will take hostId
    public List<Reservation> findByHost(Host host) {

        List<Reservation> result = reservationRepository.findByHost(host);
        result.forEach(reservation -> {
            reservation.setGuest(guestRepository.findById(reservation.getGuest().getGuestId()));
        });
        return result;
    }

    public List<Reservation> findByHostAndGuest(Host host, Guest guest) {
        List<Reservation> result = reservationRepository.findByHost(host);
        List<Reservation> reservationsForGuest = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getGuest().equals(guest)) {
                reservationsForGuest.add(result.get(i));
            }
        }
        return reservationsForGuest;
    }

    public Result<Reservation> cancelReservation(Reservation reservation) throws DataException {
        Result<Reservation> result = new Result<>();

        if (!reservationRepository.cancelReservation(reservation)) {
            String message = String.format("Reservation ID %s was not found.", reservation.getId());
            result.addErrorMessages(message);
            return result;
        }

        if (reservation.getStart().isBefore(LocalDate.now())) {
            result.addErrorMessages("Unable to delete past reservations.");
            return result;
        }
        result.setPayload(reservation);
        return result;
    }

    public Result<Reservation> editReservation(Reservation reservation) throws DataException {

        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        if (!reservationRepository.editReservation(reservation)) {
            String message = String.format(
                    "Reservation ID %s was not found.", reservation.getId());
            result.addErrorMessages(message);
        }

        result.setPayload(reservation);
        return result;
    }

    public Result<Reservation> addReservation(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(reservationRepository.addReservation(reservation));
        return result;
    }

    //helper methods- validate methods
    public Result<Reservation> validate(Reservation reservation) {

        Result<Reservation> result = validateNulls(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        validateDates(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(reservation, result);
        return result;
    }


    //check that all fields are not empty in reservation
    private Result<Reservation> validateNulls(Reservation reservation) {
        Result<Reservation> result = new Result<>();

        if (reservation == null) {
            result.addErrorMessages("Nothing to save.");
            return result;
        }

        if (reservation.getHost() == null) {
            result.addErrorMessages("Host is required.");
            return result;
        }

        if (reservation.getGuest() == null) {
            result.addErrorMessages("Guest is required.");
            return result;
        }

        if (reservation.getStart() == null) {
            result.addErrorMessages("Reservation start date is required.");
            return result;
        }

        if (reservation.getEnd() == null) {
            result.addErrorMessages("Reservation end date is required.");
            return result;
        }
        return result;
    }

    //**No Overlap: Cannot make reservation in the past - cannot have overlapping reservations
    ////( start and end cannot be same day - at least one day apart)
    private void validateDates(Reservation reservation, Result<Reservation> result) {
        if (reservation.getStart().isBefore(LocalDate.now())) {
            result.addErrorMessages("Reservations can only be made for future dates.");
        }
        if (reservation.getEnd().isBefore(reservation.getStart())) {
            result.addErrorMessages("Reservation end date has to be after the start date.");
        }

        for (Reservation r : reservationRepository.findByHost(reservation.getHost())) {
            if (r.getId() == reservation.getId()) {
                continue;
            }
            LocalDate startDate = r.getStart();
            LocalDate endDate = r.getEnd();


            if ((reservation.getStart().isBefore(startDate) && (reservation.getEnd().isAfter(startDate)))
                    || (reservation.getStart().isAfter(startDate)) && reservation.getEnd().isBefore(endDate)) {
                result.addErrorMessages("Reservation dates cannot overlap existing reservation dates.");
            } else if (reservation.getStart().isEqual(startDate) || (reservation.getStart().isEqual(endDate))
                    || (reservation.getEnd().isEqual(startDate)) || reservation.getStart().isEqual(endDate)) {
                result.addErrorMessages("Reservation dates cannot overlap existing reservation dates.");
            }
        }
    }

    //validates guests and hosts exists - checks the database
    private void validateChildrenExist(Reservation reservation, Result<Reservation> result) {

        if (guestRepository.findById(reservation.getGuest().getGuestId()) == null) {
            result.addErrorMessages("Guest doesn't exist.");
        }
        if (reservation.getHost().getHostId() == null
                || hostRepository.findByEmail(reservation.getHost().getEmail()) == null) {
            result.addErrorMessages("Host doesn't exist.");
        }
    }

    public BigDecimal createValue(Reservation reservation) {
        LocalDate date = reservation.getStart();
        BigDecimal total = BigDecimal.ZERO;

        long difference = date.until(reservation.getEnd(), ChronoUnit.DAYS);
        for (long i = 0; i < difference; i++) {
            if (date.plusDays(i).getDayOfWeek() == DayOfWeek.SATURDAY) {
                total = total.add(reservation.getHost().getWeekendRate());
            } else if (date.plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY) {
                total = total.add(reservation.getHost().getWeekendRate());
            } else {
                total = total.add(reservation.getHost().getStandardRate());
            }
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}


