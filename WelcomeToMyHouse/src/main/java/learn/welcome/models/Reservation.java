package learn.welcome.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private Host host;
    private Guest guest;
    private int id;
    private LocalDate start;
    private LocalDate end;
    private BigDecimal total;

    public Host getHost() {return host;}

    public void setHost(Host host) {this.host = host;}

    public Guest getGuest() {return guest;}

    public void setGuest(Guest guest) {this.guest = guest;}

    public int getId() {return id;}

    public int setId(int id) {this.id = id;}

    public LocalDate getStart() {return start;}

    public void setStart(LocalDate start) {this.start = start;}

    public LocalDate getEnd() {return end;}

    public void setEnd(LocalDate end) {this.end = end;}

    public BigDecimal getTotal() {return total;}

    public void setTotal(BigDecimal total) {this.total = total;}

}
