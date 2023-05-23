package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationFileRepository implements ReservationRepository {

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(@Value("${ReservationFilePath:./data/reservations}") String directory) {
        this.directory = directory;
    }

    private String getFilePath(Host host) {
        return Paths.get(directory, host.getHostId() + ".csv").toString(); //get directory and get host id and add csv
    }

    // Retrieve all reservations from a file or data source

    @Override
    public List<Reservation> findByHost(Host host) {
        List<Reservation> results = new ArrayList<>();
        if (host == null || host.getHostId().isBlank() || host.getHostId() == null) {
            return results;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(host)))) {

            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    results.add(deserialize(fields, host));
                }
            }
        } catch (IOException e) {

        }
        return results;
    }

    @Override
    public Reservation addReservation(Reservation reservation) throws DataException {
        List<Reservation> all = findByHost(reservation.getHost());
        reservation.setId(all.size() + 1);
        all.add(reservation);
        writeAll(all, reservation.getHost());
        return reservation;
    }

    //change future res
    @Override
    public boolean editReservation(Reservation reservation) throws DataException {
        List<Reservation> all = findByHost(reservation.getHost());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == reservation.getId()) {
                    all.set(i, reservation);
                    writeAll(all, reservation.getHost());
                    return true;
                }
            }
        return false;
    }
        @Override
        public boolean cancelReservation (Reservation reservation) throws DataException {
            List<Reservation> all = findByHost(reservation.getHost());
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getId() == reservation.getId()) {
                    all.remove(i);
                    writeAll(all, reservation.getHost());
                    return true;
                }
            }
            return false;
        }

        private void writeAll (List < Reservation > reservations, Host host) throws DataException {
            try (PrintWriter writer = new PrintWriter(getFilePath(host))) {
                writer.println(HEADER);

                for (Reservation reservation : reservations) {
                    writer.println(serialize(reservation));
                }
            } catch (FileNotFoundException ex) {
                throw new DataException(ex);
            }
        }

        private String serialize (Reservation reservation){
            return String.format("%s,%s,%s,%s,%s",
                    reservation.getId(),
                    reservation.getStart(),
                    reservation.getEnd(),
                    reservation.getGuest().getGuestId(),
                    reservation.getTotal());
        }

        //id,start_date,end_date,guest_id,total
        private Reservation deserialize (String[]fields, Host host){
            Reservation result = new Reservation();
            result.setId(Integer.parseInt(fields[0]));
            result.setHost(host);
            result.setStart(LocalDate.parse(fields[1]));
            result.setEnd(LocalDate.parse(fields[2]));
            result.setTotal(BigDecimal.valueOf(Double.parseDouble(fields[4])));
            Guest guest = new Guest();
            result.setGuest(guest);
            guest.setGuestId(Integer.parseInt(fields[3]));
            return result;

        }
}
