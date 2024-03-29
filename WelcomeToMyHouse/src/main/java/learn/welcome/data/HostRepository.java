package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;

import java.util.List;

public interface HostRepository {

    //find all hosts
    List<Host> findAllHost();

    //returns singular Host with email
    Host findByEmail(String email);


}
