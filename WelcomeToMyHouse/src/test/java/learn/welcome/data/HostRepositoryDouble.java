package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.State;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostRepositoryDouble implements HostRepository {
private final List<Host> host = new ArrayList<>();
    public HostRepositoryDouble(){
        Host host1 = new Host("13","Kakashi", "sensei@email.com", "(516) 0000000", State.NY, "123 Konaha Lane", "Leaf Village", "11111", new BigDecimal(800), new BigDecimal(600));
        Host host2 = new Host("17","Haku", "haku1@email.com", "(917) 0000000", State.CA, "678 Sandy Beach Road", "Sand Village", "12345", new BigDecimal(500), new BigDecimal(650));
        Host host3 = new Host("21","Orochimaru", "snakeorochimaru@email.com", "(777) 0000000", State.VA, "890 Orochimaru Street", "Sound Village", "56789", new BigDecimal(300), new BigDecimal(500));
        Host host4 = new Host("30","Tsunade", "tsunade@hokage.com", "(555) 0000000", State.CO, "1 Hokage Road", "Leaf Village", "39273", new BigDecimal(700), new BigDecimal(1000));

        host.add(host1);
        host.add(host2);
        host.add(host3);
        host.add(host4);
    }

    @Override
    public List<Host> findAllHost() {
        return host;
    }

    @Override
    public Host findByEmail(String email) {
        List<Host> host = findAllHost();
        for (int i = 0; i < host.size(); i++){
            if (host.get(i).getEmail().equals(email)){
                return host.get(i);
            }
        }
        return null;
    }
}
