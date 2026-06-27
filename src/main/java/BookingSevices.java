import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingSevices {
    private final InMemoryData bookingData;
    private final PassengerServices passengerServices;
    private final FlightServices flightServices;

    public BookingSevices(InMemoryData bookingData, PassengerServices passengerServices, FlightServices flightServices){
        this.bookingData = bookingData;
        this.passengerServices = passengerServices;
        this.flightServices = flightServices;
    }

    //return all the bookings available
    public List<Booking> getBookingList(){
        return new ArrayList<>(bookingData.getBookingMap().values());
    }
    //return booking by id
    public Optional<Booking> getBookingById(String id){
        return Optional.ofNullable(bookingData.getBookingMap().get(id));
    }

    public Optional<Booking> createBooking(String passengerId, String flightId, int seats){
        Optional<Flight> f = flightServices.getFightById(flightId);
        Optional<Passenger> p = passengerServices.getPassengerById(passengerId);
        if(!f.isPresent() || !p.isPresent() || seats<=0){
            return  Optional.empty();
        }
         Flight flight = f.get();
        if(!flightServices.reduceSeat(flightId,seats)){
            return Optional.empty();
        }
        double totalAmount = seats * flight.getPrice();
        String id = bookingData.nextFlightId();
        Booking booking = new Booking(id,flightId,passengerId,seats,totalAmount, LocalDateTime.now(),BookingStatus.CONFIRMED);
        bookingData.getBookingMap().put(id,booking);
        return Optional.of(booking);
    }

    public boolean cancelBooking(String id){
        Booking b = bookingData.getBookingMap().get(id);
        if(b == null || b.getStatus()== BookingStatus.CANCELLED){
            return false;
        }
        b.setStatus(BookingStatus.CANCELLED);
        flightServices.increaseSeat(b.getFightId(),b.getSeatsBooked());
        return true;
    }
}
