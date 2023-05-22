package learn.welcome.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Host {
    private String hostId;
    private String lastName;
    private String email;
    private String phone;
    private State state;
    private String address;
    private String city;
    private String zip;
    private BigDecimal weekendRate;
    private BigDecimal standardRate;

    public Host(String hostId, String lastName, String email, String phone, State state, String address, String city, String zip, BigDecimal weekendRate, BigDecimal standardRate) {
        this.hostId = hostId;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.state = state;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.weekendRate = weekendRate;
        this.standardRate = standardRate;
    }

    public Host(){
    }

    public String getHostId() {return this.hostId;}

    public void setHostId(String hostId) {this.hostId = hostId;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public State getState() {return state;}

    public void setState(State state) {this.state = state;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getZip() {return zip;}

    public void setZip(String zip) {this.zip = zip;}

    public BigDecimal getWeekendRate() {return weekendRate;}

    public void setWeekendRate(BigDecimal weekendRate) {this.weekendRate = weekendRate;}

    public BigDecimal getStandardRate() {return standardRate;}

    public void setStandardRate(BigDecimal standardRate) {this.standardRate = standardRate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(hostId, host.hostId) && Objects.equals(lastName, host.lastName) && Objects.equals(email, host.email) && Objects.equals(phone, host.phone) && state == host.state && Objects.equals(address, host.address) && Objects.equals(city, host.city) && Objects.equals(zip, host.zip) && Objects.equals(weekendRate, host.weekendRate) && Objects.equals(standardRate, host.standardRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostId, lastName, email, phone, state, address, city, zip, weekendRate, standardRate);
    }
}

