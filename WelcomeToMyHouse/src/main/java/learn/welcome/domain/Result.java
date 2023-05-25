package learn.welcome.domain;

import learn.welcome.models.Reservation;

public class Result<T> extends Response {
    //extend response
    //fields
    private T payload;
    //getters and setters

    public T getPayload() {return payload;}

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
//
//<T> placeholder - takes any type of class inside
//public class Response<T> {
// - 'private Reservation reservation'
//- 'private Reservation getReservation'
//- 'public void setReservation'
//- 'public void addErrorMessage' //add messages to array if there is one

