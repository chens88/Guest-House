package learn.welcome.data;

import learn.welcome.models.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationFileRepository implements ReservationRepository {
    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;
    public ReservationFileRepository(@Value("${./data/reservations}") String directory) {
        this.directory = directory;
    }
    // Retrieve all reservations from a file or data source
    @Override
    public List<Reservation> findAllReservations(String string) throws DataException {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
            reader.readLine();
            //loop and read all entry
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    Reservation reservation = deserialize(line);
                    result.add(reservation);
                }
        }catch (IOException ex){
            throw new DataException("Could Not Open Directory: " + directory, ex);
        }
        return result;
    }

    @Override
    public List<Reservation> findByHost(String reservation) throws DataException {
        List<Reservation> reservations = findAllReservations();
        List<Reservation> found = new ArrayList<>();
        for(Reservation panel : reservations){
            if(panel.getHost().equalsIgnoreCase(reservation)){
                found.add(reservation);
            }
        }
        return found;

    // Make a new reservation to the repository data
    @Override
    public Reservation addReservation(String reservation) throws DataException {
        List<Reservation> reservations = findAllReservations();
        int nextId = getNextId(reservations);
        reservation.setId.(nextId);
        reservations.add(reservation);
        writeToFile(reservations);
        return reservation;
    }

    // Update an existing reservation in the repository data
    @Override
    public boolean update(Reservation reservation) throws DataException {
            List<Reservation> allReservations = findAllReservations();
            for(int i = 0; i < Reservation.size(); i++) {
                if(reservations.get(i).getId() == reservation.getGuestId()){
                    reservation.set(i, reservation);
                    writeToFile(reservations);
                    return true;
                }
            }
            return false;
    }
    // Cancel a reservation from the repository data
    @Override
    public void cancelReservation(String reservationId) throws DataException {
        List<Reservation> reservations = findAllReservations();
        for(int i = 0; i < reservations.size(); i++) {
            if(reservations.get(i).getId() == id) {
                reservations.remove(i);
                writeToFile(reservations);
                return true;
            }
        }
        return false;
        }

    @Override

    }
//    private Panel deserialize(String line) {
//        String[] fields = line.split(",");
//        if (fields.length < 7) {
//            return null;
//        }
//        return new Panel(
//                Integer.parseInt(fields[0]),
//                fields[1],
//                Integer.parseInt(fields[2]),
//                Integer.parseInt(fields[3]),
//                Integer.parseInt(fields[4]),
//                Material.valueOf(fields[5]),
//                "true".equals(fields[6])
//        );
//    }
////- `public List<Panel> findBySection(String)` -- ???finds all Panels in a section, uses the private `findAll` method
//
//    private int getNextId(List<Panel> panels) {
//        int maxId = 0;
//        for (Panel panel : panels) {
//            if (maxId < panel.getId()) {
//                maxId = panel.getId();
//            }
//        }
//        return maxId + 1;
//    }
//
//    private void writeToFile(List<Panel> panels) throws DataException {
//        try(PrintWriter writer = new PrintWriter(filePath)){
//            writer.println("id,section,row,column,yearinstalled,material,istracking");
//
//            for(Panel panel : panels){
//                String line = serialize(panel);
//                writer.println(line);
//            }
//        }catch(IOException ex){
//            throw new DataException("Could not write to file path" + filePath);
//        }
//    }
//    private String serialize(Panel panel){
//        StringBuilder buffer = new StringBuilder();
//        buffer.append(panel.getId()).append(DELIMITER);
//        buffer.append(panel.getSection()).append(DELIMITER);
//        buffer.append(panel.getRow()).append(DELIMITER);
//        buffer.append(panel.getColumn()).append(DELIMITER);
//        buffer.append(panel.getInstallationYear()).append(DELIMITER);
//        buffer.append(panel.getMaterial()).append(DELIMITER);
//        buffer.append(panel.isTracking());
//        return buffer.toString();
//    }
}
