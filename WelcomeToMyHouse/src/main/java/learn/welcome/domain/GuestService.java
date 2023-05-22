package learn.welcome.domain;

import learn.welcome.data.GuestRepository;
import learn.welcome.models.Guest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {
    //dependencies
    private final GuestRepository repository;

    //constructor
    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    //methods
    public Guest findByEmail(String email){
        Guest guest = repository.findByEmail(email);
        return guest;
    }

    public List<Guest> findAll() {
        return repository.findAllGuests();
    }
}
