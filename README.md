# Mastery-Week

## Technical Requirements:

- Maven Project
- Spring dependency injection configured with either XML or annotations
- All financial math must use BigDecimal 
- Dates must be LocalDate, and never Strings
- All file data must be represented in models in the application 
- Must be thoroughly tested 
- Reservation ids are unique per host, not unique across the entire app
- The combination of a reservation identifier and a host identifier is required to uniquely identify a reservation

## High-Level Requirements:

- [ ] View existing reservations for a guest with a host 
- [ ] Add/create a reservation for a guest with a host 
- [ ] Edit existing reservations 
- [ ] Cancel a future reservation 

 ## Don't Wreck My House!

### DATA 

- Guest is a customer who wants to book place to stay (data provided)
- Host is the accommocation provider/property owner (data provided)
- Location is the rental property 
    *** Host and Location are combined ***
- Reservation is one or more days where guests are booked into a location/host (data provided)

- [ ] Print Main Menu (1 hours)
    - [ ] Accepts user input: 
         - 0. Exit
         - 1. View All Reservations for a Host
         - 2. Make a Reservation
         - 3. Edit a Reservation 
         - 4. Cancel a Reservation 
          Select [0-4]:

- [ ] View Reservations for Host (2 hours)
    - [ ] Admin may enter host email as unique identifier to find the host: 
        - [ ] Prints "Host Name & Email: 
            - [ ] If host is not found, display a message
        - [ ] Prints list of reservations
            - [ ] If no reservation, display a message 
            - [ ] List should include id, check-in date-check-out date, guest name, guest email
            - [ ] Sorts and displays ALL reservation in the order of date (soonest - latest)        

- [ ] Make a Reservation (4 hours)
    - [ ] Books accomodation for a guest at a host/location
        - [ ] Admin may enter a unique value (guest email) to identify guest or search for initial and pick one out of list
        - [ ] Admin may enter a unique value (host email) to identify guest or search for initial and pick one out of list
        - [ ] Should display all future reservations for the selected host so that admin can choose available dates
            - [ ] Should be able to enter a start and end date for the reservation 
        - [ ] Should calculate total cost of reservation and display a summary, and a prompt asking the admin to confirm
            - [ ] Reservation total is based on host's standard rate and weekend rate
            - [ ] Include what day of week for each day of the reservation:
                - [ ] For weekday (Sunday, Monday, Tuesday, Wednesday, or Thursday) or a weekend (Friday or Saturday) - for weekday, apply standard rate & for weekend, apply weekend rates 
        - [ ] If Y on confirmation, save the reservation and print success message   
        
- [ ] Make Reservation Validations (2 hours)
    - [ ] Guest, host, and start and end dates are required
    - [ ] Requires that guest and host must already exist in "database' - guests and hosts cannot be created
    - [ ] Start date must be before the end date
    - [ ] Reservation should not overlap existing reservation dates
    - [ ] Reservation start date must be in the future


- [ ] Edit a Reservation (3 hours)
    - [ ] Finds a reservation 
        - [ ] Admin can change guest's start and end date - & no other data can be edited
        - [ ] Recalculates total, display a summary, and ask the admin for confirmation.
            - [ ] Calculates new total cost
            - [ ] Displays summary 
            - [ ] Asks to confirm the change  

- [ ] Edit Reservation Validations (2 hours)
    - [ ] Requires guest, host, and start & end dates
    - [ ] Requires that guest and host must already exist in "database' - guests and hosts cannot be created
    - [ ] Start date must be before the end date
    - [ ] Reservation should not overlap existing reservation dates


- [ ] Cancel a Reservation (2 hours)
    - [ ] Finds a future reservation
        - [ ] Ensures that only future reservations are displayed 
    - [ ] If success, display a message 

- [ ] Cancel Reservation Validations (1 hour)
    - [ ] Ensures that only future reservations can be cancelled


```
src
├───main
│   └───java
│       └───learn
│           └───myhouseisyourhouse
│               │   App.java                      -- app entry point
│               │
│               ├───data
                        HostFileRepository.java
                        HostRepository.java
                        GuestFileRepository.java
                        GuestRepository.java
│               │       DataException.java        -- data layer custom exception
│               │       ReservationFileRepository.java       -- concrete repository
│               │       ReservationRepository.java      -- repository interface
│               │
│               ├───domain
│               │       Result.java   -- domain result for handling success/failure (for reservation) - make response class for result 
│               │       ReservationService.java    -- reservations validation/rules
                        HostService.java
                        GuestService.java

│               │
│               ├───models
│               │       Host.java             -- array of hosts
                        Guest.java            -- array of guests
│               │       Reservation.java      -- models
│               │
│               └───ui
│                       Controller.java           -- UI controller
│                       View.java                 -- all console input/output
│
└───test
    └───java
        └───learn
            └───myhouseisyourhouse
                ├───data
                │       ReservationFileRepositoryTest.java --ReservationsFileRepository tests
                        ReservationFileRepositoryTestDouble.java
                        ReservationRepositoryRepositoryTest.java
                │       ReservationRepositoryTestDouble.java -- helps tests the service, implements ReservationsRepository
                        HostFileRepositoryTest.java
                        HostFileRepositoryTestDouble.java
                        HostRepositoryTest.java
                        HostRepositoryTestDouble.java
                        GuestFileRepositoryTest.java
                        GuestFileRepositoryTestDouble.java
                        GuestRepositoryTest.java
                        GuestRepositoryTestDouble.java

                └───domain
                        ReservationServiceTest.java  -- ReservationService tests
                        ReservationServiceTestDouble.java  -- helps tests the service, implements ReservationsService
                        HostServiceTest.java
                        HostServiceTestDouble.java
                        GuestServiceTest.java
                        GuestServiceTestDouble.java


```             

# FIELDS & METHODS - List each class and all fields and methods with types

## App.java
 - 'public static void main(String[])'
 // instantiate all required classes with valid arguments, dependency injection. run controller
 
// Fields
- 'private List<Reservation> reservations;'
- 'private List<Guest> guests;'
- 'private List<Host> hosts;'

## Data Layer    
- 'print MainMenu()'
- 
## data.DataException.java       
- 'public DataException(String, Throwable)' //constructor 

### data.ReservationFileRepository.java 
- 'public class ReservationFileRepository {}'
- 'public void printMainMenu' 
- 'private String filePath'
- 'private String host'
- 'private String guest'
- 'private String hostId'
- ' public List<Reservation> getAllReservations'// Retrieve all reservations from a file or data source
- 'public String addReservation(Reservation reservation) // Make a new reservation to the repository data
- 'public String editReservation(Reservation reservation) // Update an existing reservation in the repository data
- 'public String cancelreservation(String reservationId) // Cancel a reservation from the repository data
- 'public boolean'

### data.ReservationRepository.java  
- 'public interface ReservationRepository' - //class
- 'List<Reservation> getAllReservations(String)'
- 'private Map<String, List<Reservation>> reservationsByHost;'
- 'Reservation add(Reservation)'
- 'boolean update(Reservation)'
- 'public List<Reservation> getAllReservationsForHost'
- 'public void addReservation(Reservation reservation)'
- 'public void editReservation(Reservation reservation)'
- 'public void cancelReservation(String reservationId)'

### data.HostFileRepository.java
- 'public class HostFileRepository' 
- 'public List<Host> getAllHosts' //Retrieve a host by their unique identifier 
- 'public Host getHostById(String hostId)'
- 'private String filePath'
- 'public host findby email - take string of email 
- private list findall - 

### data.HostRepository.java
- 'public class HostFileRepository' 


### data.GuestFileRepository.java
- 'private String filePath'
- 'public String GuestRepository'
- 'public List<Guest> getAllGuests()'
- 'Guest getGuestById(String guestId)'
- 'private String filePath'
- 'public String FindbyEmail - will take string of email 
- 'private List<Guest> findAll 


### data.GuestRepository.java
- 'public class GuestFileRepository'


## Domain Layer   
### domain.ReservationService.java
- 'private ReservationRepository repository' - constructor 
- 'private Reservation reservation'
- 'private ReservaitonService(ReservationRepository)'
- 'public Reservation getReservation'
- 'public void setReservation'
- 'public List findByHost (Host host)' //takes a host or hostId
- 'public result makeReservation()'
- 'public result editReservation'
- 'public result cancelReservation'
- 'public result validateReservation'

### domain.HostService.java
- 'private HostService service'
- 'private Host host'
- 'public HostService(HostRepository)'
- 'public Host getHost()' // host getter
- 'public void setHost(Guest)' // setter
- 'public void addMessage(String)'
- 'public Host findByEmail (String email)'

### domain.GuestService.java
- 'private GuestService service'
- 'private Guest guest'
- 'public GuestService(GuestRepository)'
- 'public Guest getGuest()' 
- 'public void addMessage(String)'
- 'public Guest findByEmail (String email)'

### domain.ResultResponse
- 'private Reservation reservation'
- 'private Reservation getReservation'
- 'public void setReservation'
- 'public boolean isSuccess' //get success 
- 'public List getErrorMessages' //get msg
- 'public void addErrorMessage' //add messages to array if there is one
- 'private ArrayList<String>messages'




## Models Layer 

### models.Guest.java 
- 'private int guestId'
- 'private String firstName' 
- 'private String lastName' 
- 'private String email' 
- 'private String phone'
- 'private String state (or can be enum)'
- getters and setters 
- override equals and hashCode

### models.Host.java
- 'private int guestId'
- 'private String firstName' 
- 'private String lastName' 
- 'private String email' 
- 'private String phone'
- 'private String state (or can be enum)'
- 'private String address'
- getter and setters 
- override equals and hashCode

### models.Reservation.java
- 'private int guestId'
- 'private int hostId'
- 'private String host'
- 'private String guest'
- 'private String LocalDate start'
- 'private String LocalDate end'
- 'private BigDecimal standardRate'
- 'private BigDecimal weekendRate'



## UI Layer

### ui.Controller
- 'private View view' -- required View dependency
- 'private ReservationService service' -- required service dependency
- 'public Controller(View, ReservationService)
- 'public void run()' - runs app and loops 
- private void viewByHost() 
- private void addReservation() 
- private void editReservation() 
- private void cancelReservation() 

### ui.View.java 
- 'private Scanner console' -- a Scanner to be used across all methods
- 'private static final Scanner scanner'
- 'public static int ReadInt'
- 'public static String readString(String)' {

- 'public int chooseOptionFromMenu()' -- display the main menu and select
- 'public void printHeader(String)' -- display text to the console 
- 'public void printResult(ReservationResult)'
- 'public void printReservation(String Host, List<Reservation>)'
- 'public Reservation viewByHost(String Host, List<Reservation>)
- 'public Reservation chooseReservation(String Host, List<Reservation>)'
- 'public Reservation make(Reservation)'
- 'public Reservation update(Reservation)' 



### TESTS

## Data Tests

### ReservationFileRepositoryTest.java
- 'public class ReservationFileRepositoryTest'
- 'public void testGetAllReservationsForHost'
- 'public void testMakeReservation'
- 'public void testUpdateReservation'
- 'public void testCancelReservation

## ReservationFileRepositoryTestDouble.java
- 'public class ReservationFileRepositoryTestDouble'
- 'public void testGetAllReservationsForHost'
- 'public void testMakeReservation'
- 'public void testUpdateReservation'
- 'public void testCancelReservation' 
  
## ReservationRepositoryRepositoryTest.java
- 'public class ReservationRepositoryRepositoryTest'

## ReservationRepositoryTestDouble.java 
- 'public class ReservationRepositoryRepositoryTestDouble'

## HostFileRepositoryTest.java
- 'public class HostFileRepositoryTest'
- 'public void testViewAllHosts
- 'public void testFindHostByEmail

## HostFileRepositoryTestDouble.java
- 'public class HostFileRepositoryTestDouble'

## HostRepositoryTest.java
- 'public class HostRepositoryTest'

## HostRepositoryTestDouble.java
- 'public class HostRepositoryTestDouble'

## GuestFileRepositoryTest.java
- 'public class GuestFileRepositoryTest'
- ' public void testFindGuestByEmail'

## GuestFileRepositoryTestDouble.java
- 'public class GuestFileRepositoryTestDouble'

## GuestRepositoryTest.java
- 'public class GuestRepositoryTest'
- 'public void testFindGuestByEmail'
- 'public void testViewAllGuests'

## GuestRepositoryTestDouble.java
- 'public class GuestRepositoryTestDouble'


### Domain Tests

## ReservationServiceTest.java 
- 'public class ReservationServiceTest'
- 'public void testViewReservation'
- 'public void testMakeReservation'
- 'public void testUpdateReservation'
- 'public void testCancelReservation'

## ReservationServiceTestDouble.java 
- 'public class ReservationServiceTestDouble'

## HostServiceTest.java
- 'public class HostServiceTest'

## HostServiceTestDouble.java
- 'public class HostServiceTestDouble'

## GuestServiceTest.java
- 'public class GuestServiceTest'

## GuestServiceTestDouble.java
- 'public class GuestServiceTestDouble'


*all repository need test and test doubles 