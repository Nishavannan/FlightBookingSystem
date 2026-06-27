import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter D_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {

    }

    //add flight
    public static void addFlight(Scanner sc, FlightServices fs){
        System.out.println("Source(e.g., BLR): ");
        String src = sc.nextLine();
        System.out.println("Destination (e.g., DEL): ");
        String dest = sc.nextLine();
        System.out.println("Departure (yyyy-MM-dd HH:mm)");
        LocalDateTime deptTime = LocalDateTime.parse(sc.nextLine().trim(),D_FMT);
        System.out.println("Arrival (yyyy-MM-dd HH:mm)");
        LocalDateTime arrTime = LocalDateTime.parse(sc.nextLine().trim(),D_FMT);
        System.out.println("Total seats: ");
        int totSeats = Integer.parseInt(sc.nextLine());
        System.out.println("Price per seat: ");
        double price = Double.parseDouble(sc.nextLine().trim());
        Flight f = fs.addFlight(src,dest,deptTime,arrTime,totSeats,price);

    }

    //add passengers
    public static void addPassenger(Scanner sc, PassengerServices ps){
        System.out.println("Name :");
        String name = sc.nextLine();
        System.out.println("Email :");
        String email = sc.nextLine();
        System.out.println("Phone :");
        String phone = sc.nextLine();
        Passenger p = ps.addPassenger(name,email,phone);
        System.out.println("Added: "+p);
    }

    //Search Filght
    private static void searchFlights(Scanner sc, FlightServices fs) {

        try {
            System.out.println("Source (e.g., BLR): ");
            String src = sc.nextLine();

            System.out.println("Destination (e.g., DEL): ");
            String dest = sc.nextLine();

            System.out.println("Date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(sc.nextLine().trim(), D_FMT);
            List<Flight> results = fs.search(src, dest, date);
            if (results.isEmpty()) {
                System.out.println("No flights found.");
            } else {
                results.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Search flight failed. "+ e.getMessage());
        }
        }

        //Book seats
        private static void bookSeats (Scanner sc, BookingSevices bs, FlightServices fs, PassengerServices ps){
            try {
                listFlights(fs);
                System.out.println("Enter the Flight ID to book: ");
                String flightId = sc.nextLine().trim();

                System.out.println("Enter the Passenger ID: ");
                String passengerId = sc.nextLine().trim();

                System.out.println("Enter the seates to book: ");
                int seats = Integer.parseInt(sc.nextLine().trim());

                Optional<Booking> b = bs.createBooking(flightId, passengerId, seats);
                if (b.isPresent()) {
                    System.out.println("Booking confirmed: " + b.get());
                } else {
                    System.out.println("Booking Failed. Check Flight/Passenger/Seats availability.");
                }
            } catch (Exception E) {
                System.out.println("Booking error. " + E.getMessage());
            }
        }

        //CancelBooking
        private static void cancelBooking (Scanner sc, BookingSevices bs){
            listBookings(bs);
            System.out.println("Enter the Booking ID to cancel: ");
            String id = sc.nextLine().trim();
            boolean ok = bs.cancelBooking(id);
            System.out.println(ok ? "Booking Cancelled." : "Cancelation failed (invalid ID or already cancelled)");
        }

        //List the flights
        private static void listFlights (FlightServices fs){
            List<Flight> flights = fs.getFlightList();
            if (flights.isEmpty()) System.out.println("No Flights Available");
            else flights.forEach(System.out::println);
        }

        //List the passsengers
        private static void listPassengers (PassengerServices ps){
            List<Passenger> passenger = ps.getPassengerList();
            if (passenger.isEmpty()) System.out.println("No Passenger Available");
            else passenger.forEach(System.out::println);
        }

        //List the bookings
        private static void listBookings (BookingSevices bs){
            List<Booking> bookings = bs.getBookingList();
            if (bookings.isEmpty()) System.out.println("No Bookings Available");
            else bookings.forEach(System.out::println);
        }


        //Adding sample data for initial setup
        private static void preLoadSampleData (FlightServices fs, PassengerServices ps){

            //Added some passenger
            ps.addPassenger("Rahul Dravid", "rahul@example.com", "9790180576");
            ps.addPassenger("Priya", "priya@example.com", "8903833695");

            //Added some flight
            fs.addFlight("BLR", "DEL",
                    LocalDateTime.now().plusDays(1).withHour(9).withMinute(30),
                    LocalDateTime.now().plusDays(1).withHour(12).withMinute(0),
                    120, 4500.0);

            fs.addFlight("BLR", "DEL",
                    LocalDateTime.now().plusDays(1).withHour(16).withMinute(45),
                    LocalDateTime.now().plusDays(1).withHour(19).withMinute(15), 100, 4200.0);

            fs.addFlight("BLR", "BOM",
                    LocalDateTime.now().plusDays(2).withHour(8).withMinute(15),
                    LocalDateTime.now().plusDays(2).withHour(10).withMinute(5),
                    90, 3500.0);
        }

    }
