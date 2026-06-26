import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PassengerServices {

    private final InMemoryData passengerData;

    public PassengerServices(InMemoryData passengerData){ //to initialize value as it is final
        this.passengerData = passengerData;

    }
    //Create a passenger object and store it in map

    public Passenger addPassenger(String name, String email, String phone){
        String id = passengerData.nextPassengerId();
        Passenger passenger = new Passenger(id,name.trim(),email.trim(),phone.trim());
        passengerData.getPassengerMap().put(id,passenger);
        return passenger;
    }

    // return passenger based on id
    public Optional<Passenger> getPassengerBasedOnId(String id, Map<String,Passenger> map){
        return Optional.ofNullable(passengerData.getPassengerMap().get(id));
    }

    //return List of passengers
    public List<Passenger> getPassengerList(){
        return new ArrayList<>(passengerData.getPassengerMap().values());
    }
}
