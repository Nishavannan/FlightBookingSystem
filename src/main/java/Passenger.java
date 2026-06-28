import java.util.Objects;

public class Passenger {
    private final String id;
    private String name;
    private String phno;
    private String email;

    public Passenger(String id,String name,String email, String phno){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phno = phno;

    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhno() {
        return phno;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phno='" + phno + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Passenger))  return false;
        Passenger that = (Passenger) o;
        return this.id.equals(that.id);
    }

    public int hashCode(){
        return Objects.hash(id);
    }
}
