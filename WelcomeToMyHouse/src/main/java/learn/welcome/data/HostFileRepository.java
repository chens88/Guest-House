package learn.welcome.data;

import learn.welcome.models.Guest;
import learn.welcome.models.Host;
import learn.welcome.models.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class HostFileRepository implements HostRepository {
    private static final String HEADER = "id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";
    private final String filePath;

    public HostFileRepository(@Value("${HostFilePath:./data/hosts.csv}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAllHost() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            //loop and read all entry
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[]fields = line.split(",", -1);
                if (fields.length == 10) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {

        }
        return result;
    }

    //Retrieve a host by unique identifier-host email
    @Override
    public Host findByEmail(String email) {
        return findAllHost().stream().filter(host -> host.getEmail()
                .equals(email)).findFirst().orElse(null);
        //creating list of host by calling findAll method and filtering
        //by email passed in by method

    }

    //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate
    private Host deserialize(String[] fields) {
            Host result = new Host();
            result.setHostId(fields[0]);
            result.setLastName(fields[1]);
            result.setEmail(fields[2]);
            result.setPhone(fields[3]);
            result.setAddress(fields[4]);
            result.setCity(fields[5]);
            result.setState(State.valueOf(fields[6])); //changes enum to string
            result.setZip(fields[7]);
            result.setStandardRate(new BigDecimal(fields[8]));
            result.setWeekendRate(new BigDecimal(fields[9]));
            return result;
    }
}
