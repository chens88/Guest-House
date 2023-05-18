package learn.welcome.domain;

public class ReservationService {
}

//- 'private ReservationRepository repository' - pass in constructor
//- 'private HostRepository repository' - pass in constructor
//- 'private GuestRepository repository' - pass in constructor
//- 'private ReservaitonService(ReservationRepository, GuestRepository, HostRepository)'
//- 'public List findByHost (Host host)' //takes a host or hostId
//- 'public List findByGuestandHost
//- 'public result makeReservation()'
//- 'public result edit <Reservation>' -
//- 'public result cancelReservation' - delete or cancel and pass in reservation
//- 'public result validateReservation'
//    ** needs validate methods - broken down to validatenull, uniquedate, vaidatechildrenexist, and more