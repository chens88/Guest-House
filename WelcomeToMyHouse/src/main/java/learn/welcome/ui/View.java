package learn.welcome.ui;

import learn.welcome.domain.GuestService;
import learn.welcome.domain.ReservationService;
import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class View {
    private final ConsoleIo io;

    public View(ConsoleIo io) {
        this.io = io;
    }

    public MenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MenuOption option : MenuOption.values()) {
            io.printf("%s. %s%n", option.getValue(), option.getMessage());
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }
        String message = String.format("Select [%s-%s]: ", min, max);
        return MenuOption.fromValue(io.readInt(message, min, max));
    }

    public Reservation makeReservation(Guest guest, Host host) {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setId(guest.getGuestId());
        reservation.setStart(io.readLocalDate("Start Date [MM/dd/yyyy]: "));
        reservation.setEnd(io.readLocalDate("End Date [MM/dd/yyyy]: "));
        return reservation;
    }

    public Reservation editReservation(Reservation reservation) {
        LocalDate start = io.readDate("Start Date (" + reservation.getStart() + "): ");
        LocalDate end = io.readDate("End Date (" + reservation.getEnd() + "): ");
        if (start != null) {
            reservation.setStart(start);
        }
        if (end != null) {
            reservation.setEnd(end);
        }
        return reservation;
    }

    public boolean getConfirm() {
        return io.readBoolean("Are you sure? [Y/N]");
    }

    public int getReservationID() {
        return io.readInt("Please enter Reservation ID: ");
    }

    public void enterToContinue() {
        io.readString("Please press [Enter] to continue.");
    }

    public String getHostEmail() {
        return io.readRequiredString("Enter the Host Email: ");
    }

    public String getGuestEmail() {
        return io.readRequiredString("Enter the Guest Email: ");
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error has occurred:");
        io.println(ex.getMessage());
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayReservations(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found.");
        }
        for (Reservation r : reservations) {
            io.printf("ID: %5s || Dates: %s to %s || Guest: %s %s || Email: %s || Total: $%.2f%n",
                    r.getId(),
                    r.getStart(),
                    r.getEnd(),
                    r.getGuest().getFirstName(),
                    r.getGuest().getLastName(),
                    r.getGuest().getEmail(),
                    r.getTotal());
        }
    }

    public void displayAllHost(List<Host> hosts) {
        if (hosts == null || hosts.isEmpty()) {
            io.println("No reservations found.");
        }
        for (Host h : hosts) {
            io.printf("Last Name: %s || Email: %s ||" +
                            " Address: %s, %s, %s, %s || Standard Rate: %.2f ||" +
                            " Weekend Rate: %.2f || Phone: %s%n",
                    h.getLastName(),
                    h.getEmail(),
                    h.getAddress(),
                    h.getCity(),
                    h.getState(),
                    h.getZip(),
                    h.getStandardRate(),
                    h.getWeekendRate(),
                    h.getPhone());
        }
    }

    public void displayAllGuest(List<Guest> guests) {
        if (guests == null || guests.isEmpty()) {
            io.println("No reservations found.");
        }

        for (Guest g : guests) {
            io.printf("ID: %s || Name: %s %s || Email: %s ||" +
                            " State: %s || Phone: %s%n",
                    g.getGuestId(),
                    g.getFirstName(),
                    g.getLastName(),
                    g.getEmail(),
                    g.getState(),
                    g.getPhone());

        }
    }
}
