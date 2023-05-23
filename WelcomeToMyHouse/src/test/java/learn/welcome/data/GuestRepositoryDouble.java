package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.State;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {
    public GuestRepositoryDouble(){
        Guest guest1 = new Guest(10,"Sai", "Shinobi", "sshinobi@email.com", "(917) 0000000", State.NY);
        Guest guest2 = new Guest(11, "Naruto", "Uzumaki", "naruto@email.com", "(516) 9999999", State.CA);
        Guest guest3 = new Guest(12, "Sasuke", "Uchiha", "suchiha@email.com", "718 7777777", State.AZ);

        guests.add(guest1);
        guests.add(guest2);
        guests.add(guest3);
    }

//fields
private final List<Guest> guests = new ArrayList<>();

// methods

    @Override
    public List<Guest> findAllGuests() {
        return guests;
    }

    @Override
    public Guest findByEmail(String email){
        List<Guest> guestList = findAllGuests();
        for (int i = 0; i < guestList.size(); i++){
            if (guestList.get(i).getEmail().equals(email)){
                return guestList.get(i);
            }
        }
        return null;
    }

    @Override
    public Guest findById(int id) {
        return findAllGuests().stream().filter(guest -> guest.getGuestId() == id).findFirst().orElse(null);
    }

}