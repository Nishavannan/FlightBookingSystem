import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightServices {
    private final InMemoryData flightData;

    public FlightServices(InMemoryData flightData){
        this.flightData = flightData;
    }

    //create a flight object and store it in map

    public Flight addFlight( String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats, double price ){
        String id = flightData.nextFlightId();
        Flight flight = new Flight(id,source.trim().toUpperCase(),destination.trim().toUpperCase(),departureTime,arrivalTime,totalSeats,price);
        flightData.getFlightMap().put(id,flight);
        return flight;
    }

    //Search Flight by id

    public Optional<Flight> getFightById(String id){
    return Optional.ofNullable(flightData.getFlightMap().get(id));
    }

    //List the availavble Flight

    public List<Flight> getFlightList(){
        return new ArrayList<>(flightData.getFlightMap().values());
    }

    //If User book seat Reduce number of seat

    public boolean reduceSeat(String flightId, int seats){
        Flight flight = flightData.getFlightMap().get(flightId);
        if(flight == null){
            return false;
        }if (seats <=0 || seats > flight.getAvailableSeats()){
            return false;
        }
        flight.setAvailableSeats(flight.getTotalSeats()-seats);
        return true;
    }

    //If User Cancel a booking increase seat
    public void increaseSeat(String flightId, int seats){
        Flight flight = flightData.getFlightMap().get(flightId);
        if(flight != null && seats >0 )
            flight.setAvailableSeats(flight.getAvailableSeats()+seats);
    }

    public List<Flight> search(String source, String destination, LocalDateTime date){
        String src = source.trim().toUpperCase();
        String dest = destination.trim().toUpperCase();
        return flightData.getFlightMap().values().stream()
                .filter(f -> f.getSource().equals(src))
                .filter(f -> f.getDestination().equals(dest))
                .filter(f -> f.getDepartureTime().toLocalDate().equals(date))
                .sorted(Comparator.comparing(Flight::getDepartureTime )).collect(Collectors.toList());
    }
}
