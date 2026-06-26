import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryData {
    private Map<String, Passenger> passengerMap = new LinkedHashMap<>();
    private final AtomicInteger passengerId = new AtomicInteger(0);
    private Map<String ,Flight> flightMap = new LinkedHashMap<>();
    private final AtomicInteger flightId = new AtomicInteger(0);

    public String nextPassengerId(){
        return "P-" + passengerId.getAndIncrement();
    }

    public Map<String, Passenger> getPassengerMap(){
        return passengerMap;
    }

    public String nextFlightId(){
        return "F-" + flightId.getAndIncrement();
    }

    public Map<String, Flight> getFlightMap(){
        return flightMap;
    }


}
