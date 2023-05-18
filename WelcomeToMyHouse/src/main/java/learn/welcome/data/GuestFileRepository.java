package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class GuestFileRepository implements GuestRepository {
    //guest_id,first_name,last_name,email,phone,state
    private static final String HEADER = "guest_id,first_name,last_name,email,phone,state";
    private final String filePath;
    public GuestFileRepository(@Value("${guestFilePath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Guest> findAllGuests() {
        ArrayList<Guest> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            //loop and read all entry
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[]fields = line.split(",", -1);
                if (fields.length == 6){
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {

        }
        return result;
    }

    @Override
    public List<Guest> findByEmail(String email) {
        ArrayList<Guest> result = new ArrayList<>();
        List<Guest> all = findAllGuests();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getEmail().equals(email)){
                result.add(all.get(i));
            }
        }
        return result;
    }

    //guest_id,first_name,last_name,email,phone,state
    private Guest deserialize(String[] fields) {
            Guest result = new Guest();
            result.setGuestId(Integer.parseInt(fields[0]));
            result.setFirstName(fields[1]);
            result.setLastName(fields[2]);
            result.setEmail(fields[3]);
            result.setPhone(fields[4]);
            result.setState(State.valueOf(fields[5]));
            return result;
    }

}

