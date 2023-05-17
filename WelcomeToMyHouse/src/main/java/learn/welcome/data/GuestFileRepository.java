package learn.welcome.data;

import learn.welcome.models.Guest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestFileRepository implements GuestRepository {
    private static final String HEADER = "guest_id,first_name,last_name,email,phone,state";
    private final String filePath;
    public GuestFileRepository(@Value("${guestFilePath:./data.guests.csv}") String filePath) {
        this.filePath = filePath;
    }

//    private List<Guest> findAll();
//
//                    - 'public String GuestRepository'
//                    - 'public List<Guest> getAllGuests()'
//                    - 'Guest getGuestById(String guestId)'
//                    - 'private String filePath'
//                    - 'public String FindbyEmail - will take string of email
//                    - 'private List<Guest> findAll

    @Override
    public Guest findGuestById(String guestId) throws DataException{
        return null;
    }

    @Override
    public List<GuestR> findAllGuests() throws DataException {
        return null;
    }

    @Override
    public List<Guest> findByEmail(String email) throws DataException {
        return null;
    }

    @Override
    public boolean update(Guest guest) throws DataException {
        return false;
    }
}
