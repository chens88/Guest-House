# Guest Reservations Service App

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


### TASK LIST
- [ ] Set up Maven project [1.5 hour]
    - [ ] Create packages and classes
    - [ ] Download data files (add to project)
        - [ ] Create data folder at root level 
        - [ ] Add files to folder 
    - [ ] Add dependencies - SpringDI & JUnit
    - [ ] Add Spring dependency injection with XML
    - [ ] Add .XML file 

- [ ] Create Models [1 hour]
    - [ ] Add all fields
    - [ ] Add all getters and setters 
    - [ ] Add enum for States
    - [ ] *Might want to override toString - to get back something meaningful

- [ ] Create Data Layer [6 hours]
    - [ ] Build GuestRepository and GuestFileRepository [30 min]
    - [ ] Build HostRepository and HostFileRepository [30 min]
    - [ ] Test GuestFileRepository [30 min]
    - [ ] Test HostFileRepository [30 min]
    - [ ] Build ReservationRepository and ReservationFileRepository [2 hours]
    - [ ] Test ReservationFileRepository [1 hour]

- [ ] Create Domain Layer [8-10 hours]
    - [ ] Build GuestService [1 hour]
    - [ ] Build HostService [1 hour]
    - [ ] Build Results [30 min]
    - [ ] Build Repository HostRepositoryDouble [30 min]
    - [ ] Build Repository GuestRepositoryDouble [30 min]
    - [ ] Test GuestService [1 hour]
    - [ ] Test HostService [1 hour]
    - [ ] Build ReservationService [2-3 hour]
    - [ ] Build Repository Double for ReservationService [30 min]
    - [ ] Test ReservationService [1-2 hour]

- [ ] Create UI Layer [4 hours]
    - [ ] Create controller class
    - [ ] Create view class
    - [ ] Work back and forth between the two classes     


- Guest is a customer who wants to book place to stay (data provided)
- Host is the accommocation provider/property owner (data provided)
- Location is the rental property 
    *** Host and Location are combined ***
- Reservation is one or more days where guests are booked into a location/host (data provided)

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
 - 'print MainMenu()'

## Data Layer    
 
## data.DataException.java       
- 'public DataException(String, Throwable)' //constructor 

### data.ReservationFileRepository.java 
- 'public class ReservationFileRepository {}'
- 'public void printMainMenu' 
- 'private String filePath'
- ' public List<Reservation> getAllReservations'// Retrieve all reservations from a file or data source
- 'public String addReservation(Reservation reservation) // Make a new reservation to the repository data
- 'public String editReservation(Reservation reservation) // Update an existing reservation in the repository data
- 'public String cancelreservation(String reservationId) // Cancel a reservation from the repository data

### data.ReservationRepository.java  
- 'public interface ReservationRepository' - //class
- 'List<Reservation> getAllReservations(String)'
- 'private <String, List<Reservation>> reservationsByHost;'
- 'private <String, List<Reservation>> findAll'
- 'private <String, List<Reservation>> findByHost'
- 'Reservation add(Reservation)'
- 'boolean update(Reservation)'
- 'public List<Reservation> getAllReservationsForHost'
- 'public void addReservation(Reservation reservation)'
- 'public void editReservation(Reservation reservation)'
- 'public void cancelReservation(String reservationId)'

### data.HostFileRepository.java
- 'public class HostFileRepository' 
- 'public List<Host> getAllHosts' //Retrieve a host by their unique identifier 
- 'public Host getHostByEmail(String hostEmail)'
- 'private String filePath'
- 'public host findby email - take string of email 
- private list findall 

### data.HostRepository.java
- 'public class HostFileRepository' 
- 'method signature


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
- 'private ReservationRepository repository' - pass in constructor 
- 'private HostRepository repository' - pass in constructor 
- 'private GuestRepository repository' - pass in constructor 
- 'private ReservaitonService(ReservationRepository, GuestRepository, HostRepository)'
- 'public List findByHost (Host host)' //takes a host or hostId
- 'public List findByGuestandHost 
- 'public result makeReservation()'
- 'public result edit <Reservation>' - 
- 'public result cancelReservation' - delete or cancel and pass in reservation 
- 'public result validateReservation'
    ** needs validate methods - broken down to validatenull, uniquedate, vaidatechildrenexist, and more 


### domain.HostService.java
- 'public HostService(HostRepository)'
- public Host findByEmail (String email)
- public hostRepository - Needs host repo


### domain.GuestService.java
- 'public GuestService(GuestRepository)'
- 'public Guest findByEmail (String email)'
- public GuestRepository 

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
- 'private State state' //enum
- getters and setters 
- override equals and hashCode
add state enums class 
anywhere where you see string state it would be state state (enum)

### models.Host.java
- 'private String hostId'
- 'private String lastName' 
- 'private String email' 
- 'private String phone'
- 'private State state' //enum
- 'private String address'
- 'private String city'
- 'private String zip'
- 'private BigDecimal weekendRate'
- 'private BigDecimal StandardRate'
- getter and setters 
    **add state enums class 
    **anywhere where you see string state it would be state state (anywhere state exists )

### models.Reservation.java
- 'private Host host'
- 'private Guest guest'
- 'private int id'
- 'private LocalDate start'
- 'private LocalDate end'
- 'private BigDecimal total'


## UI Layer

### ui.Controller
- 'private View view' -- required View dependency
- 'private ReservationService service' -- required service dependency
- 'private HostService service' -- required service dependency
- 'private GuestService service' -- required service dependency
- 'public Controller(View, ReservationService)
- 'public void run()' - runs app and loops 
- private void viewByHost() 
- private void addReservation() 
- private void editReservation() 
- private void cancelReservation() 

<!-- needs fields 
guest service
host service
reservation service -->
view 
** afterwards add these to the constructor 

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
- 'public Reservation make(Reservation)'
- 'public Reservation update(Reservation)' 
// add a field for Daytime formatter in addtion to scanner or console io 
// 

### TESTS

## Data Tests
**TEST USE SHOULD AND SHOULDNOT 
### ReservationFileRepositoryTest.java
- 'public class ReservationFileRepositoryTest'
- 'public void testGetAllReservationsForHost'
- 'public void shouldMakeReservation'
- 'public void shouldNotMakeReservation'
- 'public void shouldUpdateReservation'
- 'public void shouldNotUpdateReservation'

- 'public void testCancelReservation
Should gethost
shouldnotgethost
should

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
- 'public void ShouldViewReservation'
- 'public void ShouldMakeReservation'
- 'public void ShouldtestUpdateReservation'
- 'public void ShouldtestCancelReservation'
**Needs to test all validations**

## ReservationServiceTestDouble.java 
- 'public class ReservationServiceTestDouble'

## HostServiceTest.java
- 'public class HostServiceTest'
- 'public void testFindByEmail'

## HostServiceTestDouble.java
- 'public class HostServiceTestDouble'

## GuestServiceTest.java
- 'public class GuestServiceTest'
- 'public void testFindByEmail'

## GuestServiceTestDouble.java
- 'public class GuestServiceTestDouble'


*all repository need test and test doubles 
