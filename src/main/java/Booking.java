import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private final String id;
    private final String fightId;
    private final String passengerId;
    private final int seatsBooked;
    private final double amount;
    private final LocalDateTime bookingTime;
    private BookingStatus status;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Booking(String id, String flightId, String passengerId, int seatsBooked, double amount ,LocalDateTime bookingTime, BookingStatus status){
        this.id = id;
        this.fightId = flightId;
        this.passengerId = passengerId;
        this.seatsBooked = seatsBooked;
        this.amount = amount;
        this.bookingTime = bookingTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getFightId() {
        return fightId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", fightId='" + fightId + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", seatsBooked=" + seatsBooked +
                ", amount=" + amount +
                ", bookingTime=" + bookingTime.format(fmt) +
                ", status=" + status +
                '}';
    }
}
