import prob1.SingletonClass;
import prob2.Truck;
import prob2.Vehicle;
import prob2.VehicleFactory;

/**
 * @author kush
 */
public class Main {
    public static void main(String[] args) {
        SingletonClass key1 = SingletonClass.getSingletonObject();
        SingletonClass key2 = SingletonClass.getSingletonObject();

        Vehicle vehicle= VehicleFactory.getVehicle("bus");
    }
}

/*Need to create a Singleton obeject and produce the seven digit anti virus random key and won’t
be negative. Your program must not allow to get more than one instance. Here below is the Test
class and the expected output.*/
