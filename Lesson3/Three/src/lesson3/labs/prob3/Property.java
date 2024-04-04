package lesson3.labs.prob3;

/**
 * @author kush
 */
public class Property {

    protected Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Property(Address address) {
        this.address = address;
    }
    public double computeRent(){
        return 0.0;
    }
}
