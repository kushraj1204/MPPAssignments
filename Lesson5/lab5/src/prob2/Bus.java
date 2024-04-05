package prob2;

/**
 * @author kush
 */
public class Bus implements Vehicle{
    Bus() {
        startEngine();
    }

    @Override
    public void startEngine() {
        System.out.println("Bus Engine started");
    }
}
