package prob2;

/**
 * @author kush
 */
public class Car implements Vehicle{
    Car() {
        startEngine();
    }

    @Override
    public void startEngine() {
        System.out.println("Car Engine started");
    }
}
