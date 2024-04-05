package prob2;

/**
 * @author kush
 */
public class Truck implements Vehicle{

    Truck() {
        startEngine();
    }

    @Override
    public void startEngine() {
        System.out.println("Truck Engine started");
    }
}
