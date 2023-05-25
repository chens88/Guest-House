package learn.welcome.ui;

import learn.welcome.data.DataException;
import learn.welcome.domain.GuestService;
import learn.welcome.domain.HostService;
import learn.welcome.domain.ReservationService;
import learn.welcome.domain.Result;
import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Controller {
    private final GuestService guestService;
    private final HostService hostService;
    private final ReservationService reservationService;
    private final View view;

    public Controller(GuestService guestService, HostService hostService, ReservationService reservationService, View view) {
        this.guestService = guestService;
        this.hostService = hostService;
        this.reservationService = reservationService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to My House Reservation Services!");
        try {
            runAppLoop();
        } catch(DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Thank you for using our service. Goodbye!");
    }

    private void runAppLoop() throws DataException {
        MenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_GUEST:
                    viewGuests();
                    break;
                case VIEW_HOST:
                    viewHosts();
                    break;
                case VIEW_RESERVATIONS:
                    viewReservations();
                    break;
                case ADD_RESERVATION:
                    addReservations();
                    break;
                case EDIT_RESERVATION:
                    editReservations();
                    break;
                case CANCEL_RESERVATION:
                    cancelReservations();
                    break;
            }
        } while (option != MenuOption.EXIT);
    }
    private void viewHosts() {
        List<Host> hosts = hostService.findAll();
        view.displayAllHost(hosts);
        view.enterToContinue();
    }

    private void viewGuests() {
        List<Guest> guests = guestService.findAll();
        view.displayAllGuest(guests);
        view.enterToContinue();
    }

    private void viewReservations() {
        view.displayHeader(MenuOption.VIEW_RESERVATIONS.getMessage());
        String email = view.getHostEmail();
        Host host = hostService.findByEmail(email);
        if (host != null) {
            view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
            List<Reservation> reservations = reservationService.findByHost(host);
            if (reservations.isEmpty()){
                view.displayHeader("There are no reservations for this host. ");
            } else {
                view.displayReservations(reservations);
            }
        } else{
            view.displayHeader("There is no host with this email. Please enter an existing host email. ");
        }
        view.enterToContinue();
    }

    private void addReservations() throws DataException {
        view.displayHeader(MenuOption.ADD_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        if (guest == null) {
            return;
        }
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        if (host == null) {
            return;
        } else {
            view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
            List<Reservation> reservations = reservationService.findByHost(host);
            view.displayReservations(reservations);
            System.out.printf("%nStandard Rate: $%.2f%nWeekend Rate: $%.2f%n",
                    host.getStandardRate(), host.getWeekendRate());
        }

        Reservation reservation = view.makeReservation(guest, host);
        BigDecimal value = reservationService.createValue(reservation);
        reservation.setTotal(value);
        Result<Reservation> valid = reservationService.validate(reservation);
        if (!valid.isSuccess()){
            view.displayStatus(false, valid.getErrorMessages());
            System.out.println("Returning to Main Menu. ");
            return;
        }
        System.out.printf("%nTotal: $%.2f%n", reservation.getTotal());
        if (view.getConfirm()) {
            Result<Reservation> result = reservationService.addReservation(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s created.", result.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        } else {
            System.out.println("Returning to Main Menu.");
        }
    }
    private void editReservations() throws DataException {
        view.displayHeader(MenuOption.EDIT_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        if (guest == null) {
            return;
        }
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        if (host == null) {
            return;
        }
        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> reservations = reservationService.findByHost(host);
        List<Reservation> reservationStream =
                reservations.stream()
                        .filter(reservation -> reservation.getGuest().getGuestId() == guest.getGuestId())
                        .collect(Collectors.toList());
        view.displayReservations(reservationStream);
        if (reservations.isEmpty()) {
            return;
        }
        System.out.printf("%nStandard Rate: $%.2f%nWeekend Rate: $%.2f%n",
                host.getStandardRate(), host.getWeekendRate());
        int reservationNumber = view.getReservationID();
        Reservation reservation = reservationStream.stream()
                .filter(i -> Objects.equals(i.getId(), reservationNumber))
                .findFirst()
                .orElse(null);
        if (reservation == null) {
            System.out.printf("Reservation with ID#%s is not found.%n", reservationNumber);
            return;
        }
        view.editReservation(reservation);
        BigDecimal value = reservationService.createValue(reservation);
        reservation.setTotal(value);
        System.out.printf("%nTotal: $%.2f%n", reservation.getTotal());
        if (view.getConfirm()) {
            Result<Reservation> result = reservationService.editReservation(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s is updated.", result.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        } else {
            System.out.println("Returning to Main Menu.");
        }

    }
    private void cancelReservations() throws DataException {
        view.displayHeader(MenuOption.CANCEL_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        if (guest == null) {
            return;
        }
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        if (host == null) {
            return;
        }

        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> reservations = reservationService.findByHost(host);
        List<Reservation> reservationStream =
                reservations.stream()
                        .filter(reservation -> reservation.getGuest().getGuestId() == guest.getGuestId())
                        .filter(reservation -> reservation.getStart().isAfter(LocalDate.now()))
                        .collect(Collectors.toList());
        view.displayReservations(reservationStream);
        if (reservationStream.isEmpty()) {
            view.displayHeader("Can't cancel reservation: past reservations or reservations with invalid host/guest combo cannot be cancelled.");
            return;
        }
        int reservationNumber = view.getReservationID();
        Reservation reservation = reservationStream.stream()
                .filter(i -> Objects.equals(i.getId(), reservationNumber))
                .findFirst()
                .orElse(null);
        if (reservation == null) {
            System.out.printf("Reservation with ID#%s is not found.%n", reservationNumber);
            return;
        }
        if (view.getConfirm()) {
            Result<Reservation> result = reservationService.cancelReservation(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s is deleted.", result.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        } else {
            System.out.println("Returning to Main Menu.");
        }
    }

}
