package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import learn.welcome.models.State;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryDouble implements ReservationRepository{
    private final List<Reservation> reservations = new ArrayList<>();

    public ReservationRepositoryDouble(){
        Host host1 = new Host("13","Kakashi", "sensei@email.com", "(516) 0000000", State.NY, "123 Konaha Lane", "Leaf Village", "11111", new BigDecimal(800), new BigDecimal(600));
        Guest guest1 = new Guest(10,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        Reservation reservation1 = new Reservation(100, LocalDate.of(2022, 9,1), LocalDate.of(2022, 10,1), host1, guest1, new BigDecimal(5000));

        Host host2 = new Host("17","Haku", "haku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Guest guest2 = new Guest(11, "Naruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        Reservation reservation2 = new Reservation(300, LocalDate.of(2023, 1,13), LocalDate.of(2023, 3,20), host2, guest2, new BigDecimal(8000));

        Host host3 = new Host("30","Tsunade", "tsunade@hokage.com", "(555) 0000000", State.CO, "1 Hokage Road", "Leaf Village", "39273", new BigDecimal(700), new BigDecimal(1000));
        Guest guest3 = new Guest(55, "Choji", "Butterfly", "chojibutterfly@email.com", "(111) 2223333", State.TX);
        Reservation reservation3 = new Reservation(800, LocalDate.of(2025, 7,9), LocalDate.of(2025, 9,20), host3, guest3, new BigDecimal(15000));

        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);


    }

    @Override
    public List<Reservation> findByHost(Host host) {
        List<Reservation> hostReservations = new ArrayList<>();
        for (int i = 0; i < reservations.size(); i++){
            if(reservations.get(i).getHost().equals(host)){
                hostReservations.add(reservations.get(i));
            }
        }
        return hostReservations;
    }

    @Override
    public Reservation addReservation(Reservation reservation) throws DataException {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public boolean editReservation(Reservation reservation) throws DataException {
        int res = -1;
        for (int i = 0; i < reservations.size(); i++){
            if (reservations.get(i).getId() == reservation.getId()) {
                res = i;
                break;
            }
        }
        if (res != -1) {
            reservations.set(res, reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelReservation(Reservation reservation) throws DataException {
        int res = -1;
        for (int i = 0; i < reservations.size(); i++){
            if (reservations.get(i).getId() == reservation.getId()) {
                res = i;
                break;
            }
        }
        if (res != -1) {
            reservations.remove(res);
            return true;
        }
        return false;
    }
}
