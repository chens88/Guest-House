package learn.welcome.domain;

import learn.welcome.data.GuestRepositoryDouble;
import learn.welcome.data.HostRepositoryDouble;
import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.State;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class HostServiceTest {
    HostService service = new HostService (new HostRepositoryDouble());

    // new BigDecimal(800), new BigDecimal(600));
    @Test
    void shouldFindByEmail() {
        Host host = service.findByEmail("sensei@email.com");
        assertNotNull(host);
        assertEquals("13", host.getHostId());
        assertEquals("Kakashi", host.getLastName());
        assertEquals("(516) 0000000", host.getPhone());
        assertEquals(State.NY, host.getState());
        assertEquals("123 Konaha Lane", host.getAddress());
        assertEquals("Leaf Village", host.getCity());
        assertEquals("11111", host.getZip());
        assertEquals(host.getWeekendRate(), new BigDecimal(800));
        assertEquals(host.getStandardRate(), new BigDecimal(600));
    }

    @Test
    void shouldNotFindUnknownEmail() {
        Host host= service.findByEmail("unknownemail@domain.com");
        assertNull(host); //host shouldn't exist
    }
}
