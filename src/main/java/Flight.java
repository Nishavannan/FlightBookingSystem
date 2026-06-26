import javax.print.DocFlavor;
import javax.swing.text.DateFormatter;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight {
    private final String id;
    private  String source;
    private  String destination;
    private  LocalDateTime departureTime;
    private  LocalDateTime arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double price;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Flight(String id, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats, double price){
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime.format(FMT) +
                ", arrivalTime=" + arrivalTime.format(FMT) +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Flight)) return false;
        Flight that = (Flight) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
