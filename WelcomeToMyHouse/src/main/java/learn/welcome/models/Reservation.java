package learn.welcome.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private int id;
    private LocalDate start;
    private LocalDate end;
    private Host host;
    private Guest guest;
    private BigDecimal total;

    public Reservation(int id, LocalDate start, LocalDate end, Host host, Guest guest, BigDecimal total) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.host = host;
        this.guest = guest;
        this.total = total;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, host);
    }
}
