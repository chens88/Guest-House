package learn.welcome.models;

import java.math.BigDecimal;

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
    private BigDecimal StandardRate;


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

    public BigDecimal getStandardRate() {return StandardRate;}

    public void setStandardRate(BigDecimal standardRate) {StandardRate = standardRate;}

}

