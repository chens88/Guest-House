# Mastery-Week

Technical Requirements:
- Maven Project
- Spring dependency injection configured with either XML or annotations
- All financial math must use BigDecimal 
- Dates must be LocalDate, and never Strings
- All file data must be represented in models in the application 
- Must be thoroughly tested 
- Reservation ids are unique per host, not unique across the entire app
- The combination of a reservation identifier and a host identifier is required to uniquely identify a reservation

High-Level Requirements:
[ ] View existing reservations for a guest with a host 
[ ] Add/create a reservation for a guest with a host 
[ ] Edit existing reservations 
[ ] Cancel a future reservation 

Don't Wreck My House!

DATA 
- Guest is a customer who wants to book place to stay (data provided)
- Host is the accommocation provider/property owner (data provided)
- Location is the rental property 
    *** Host and Location are combined ***
- Reservation is one or more days where guests are booked into a location/host (data provided)

[ ] Print Main Menu (1 hours)
    - [ ] Accepts user input: 
         - 0. Exit
         - 1. View All Reservations for a Host
         - 2. Make a Reservation
         - 3. Edit a Reservation 
         - 4. Cancel a Reservation 
          Select [0-4]:

[ ] View Reservations for Host (2 hours)
    - [ ] Admin may enter host email as unique identifier to find the host: 
        - [ ] Prints "Host Name & Email: 
            - [ ] If host is not found, display a message
        - [ ] Prints list of reservations
            - [ ] If no reservation, display a message 
            - [ ] List should include id, check-in date-check-out date, guest name, guest email
            - [ ] Sorts and displays ALL reservation in the order of date (soonest - latest)        

[ ] Make a Reservation (4 hours)
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
        
[ ] Make Reservation Validations (2 hours)
    - [ ] Guest, host, and start and end dates are required
    - [ ] Requires that guest and host must already exist in "database' - guests and hosts cannot be created
    - [ ] Start date must be before the end date
    - [ ] Reservation should not overlap existing reservation dates
    - [ ] Reservation start date must be in the future


[ ] Edit a Reservation (3 hours)
    - [ ] Finds a reservation 
        - [ ] Admin can change guest's start and end date - & no other data can be edited
        - [ ] Recalculates total, display a summary, and ask the admin for confirmation.
            - [ ] Calculates new total cost
            - [ ] Displays summary 
            - [ ] Asks to confirm the change  

[ ] Edit Reservation Validations (2 hours)
    - [ ] Requires guest, host, and start & end dates
    - [ ] Requires that guest and host must already exist in "database' - guests and hosts cannot be created
    - [ ] Start date must be before the end date
    - [ ] Reservation should not overlap existing reservation dates


[ ] Cancel a Reservation (2 hours)
    - [ ] Finds a future reservation
        - [ ] Ensures that only future reservations are displayed 
    - [ ] If success, display a message 

[ ] Cancel Reservation Validations (1 hour)
    - [ ] Ensures that only future reservations can be cancelled


src
├───main
│   └───java
│       └───learn
│           └───myhouseisyourhouse
│               │   App.java                      -- app entry point
│               │
│               ├───data
│               │       DataException.java        -- data layer custom exception
│               │       ReservationsFileRepository.java       -- concrete repository
│               │       ReservationsRepository.java      -- repository interface
│               │
│               ├───domain
│               │       ReservationsResult.java   -- domain result for handling success/failure
│               │       ReservationsService.java    -- reservations validation/rules
                        HostResult.java
                        HostService.java
                        GuestResult.java
                        GuestService.java

│               │
│               ├───models
│               │       Hosts.java             -- array of hosts
│               │       Reservations.java      -- model
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
                │       ReservationsFileRepositoryTest.java --ReservationsFileRepository tests
                │       ReservationsRepositoryTestDouble.java -- helps tests the service, implements ReservationsRepository
                │
                └───domain
                        ReservationsServiceTest.java  -- ReservationService tests
                        ReservationsServiceTestDouble.java  -- helps tests the service, implements ReservationsService