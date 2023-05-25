package learn.welcome.models;

import java.util.Objects;

public class Guest {
    private int guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private State state;


    public Guest(int guestId, String firstName, String lastName, String email, String phone, State state) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.state = state;
    }

    public Guest(){

    }

    public int getGuestId() {return guestId;}

    public void setGuestId(int guestId) {this.guestId = guestId;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public State getState() {return state;}
    public void setState(State state) {this.state = state;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return guestId == guest.guestId && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && Objects.equals(email, guest.email) && Objects.equals(phone, guest.phone) && state == guest.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId, firstName, lastName, email, phone, state);
    }

    //    @Override
//    public override getEquals() {return equals;}
//    public void setEquals(override equals) {this.equals = equals;}
//    public and getHashCode() {return hashCode;}
//    public void setHashCode(and hashCode) {this.hashCode = hashCode;}

}
