package learn.welcome.domain;

import learn.welcome.data.HostRepository;
import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HostService {
    //dependencies
    private final HostRepository repository;

    //constructor
    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    //methods
    public Host findByEmail(String email){
        Host host = repository.findByEmail(email);
        return host;
    }

    public List<Host> findAll() {
        return repository.findAllHost();
    }
}

//methods public Host findByEmail(String email) ```
//- 'public HostService(HostRepository)'
//        - public Host findByEmail (String email)
//        - public hostRepository - Needs host repo