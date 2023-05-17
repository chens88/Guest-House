package learn.welcome.models;

public class Guest {
    private int guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private State state;

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


//    @Override
//    public override getEquals() {return equals;}
//    public void setEquals(override equals) {this.equals = equals;}
//    public and getHashCode() {return hashCode;}
//    public void setHashCode(and hashCode) {this.hashCode = hashCode;}

}
