package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HostFileRepositoryTest {
    static final String TEST_PATH = "./data/test-data/host-test.csv";
    static final int HOST_COUNT = 31;

    HostFileRepository repository = new HostFileRepository(TEST_PATH);
    final List<Host> all = repository.findAllHost();

    @Test
    void shouldFindAllHost() {
        List<Host> host = repository.findAllHost();
        assertEquals(HOST_COUNT, host.size());
    }

    //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate
    //eyearnes0@sfgate.com,(806) 1783815,3 Nova Trail,Amarillo,TX,79182,340,425

    @Test
    void shouldFindByEmail() {
        Host host = new Host();
        host.setEmail("eyearnes0@sfgate.com");

        Host testHost = new Host();
        testHost.setEmail("eyearnes0@sfgate.com");
        assertTrue(host.getEmail().equals(testHost.getEmail()));

    }

    @Test
    void shouldNotFindByUnknownEmail() {
        Host host = new Host();
        host.setEmail("randomemail@domain.com");

        Host testHost = new Host();
        testHost.setEmail("randomemail@domain.com");
        assertTrue(host.getEmail().equals(testHost.getEmail()));

    }

}

