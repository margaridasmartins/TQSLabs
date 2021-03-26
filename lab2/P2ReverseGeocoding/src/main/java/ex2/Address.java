package ex2;
import java.util.Objects;

public  class Address {

    private String road;
    private String city;
    private String state;
    private String zip;
    private String houseNumber;

    public Address (String road, String city, String state, String zip, String houseNumber){
        this.road=road;
        this.city=city;
        this.state=state;
        this.zip=zip;
        this.houseNumber=houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "road=" + road + ", city=" + city + ", state=" + state + ", zip=" + zip + ", houseNumber=" + houseNumber + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.road, other.road)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.houseNumber, other.houseNumber)) {
            return false;
        }
        return true;
    }


}