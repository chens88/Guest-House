package learn.welcome.data;

import learn.welcome.models.Guest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HostFileRepository implements HostRepository{
    private static final String HEADER = "id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";
        private final String filePath;
        public HostFileRepository(@Value("${guestFilePath:./data.hosts.csv}") String filePath) {
            this.filePath = filePath;
        }

    //Retrieve a host by their unique identifier
    @Override
    public Guest findHostbyEmail(String hostEmail) throws DataException{
        return null;
    }
    @Override
    public List<Host> findAllHosts() throws DataException {
        return null;
    }

    @Override
    public List<Host> findByEmail(String email) throws DataException {
        return null;
    }


}
