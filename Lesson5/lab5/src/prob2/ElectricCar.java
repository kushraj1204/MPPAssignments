package prob2;

/**
 * @author kush
 */
public class ElectricCar implements Vehicle {
    ElectricCar() {
        startEngine();
    }

    @Override
    public void startEngine() {
        System.out.println("Electric car Engine started");
    }
}
