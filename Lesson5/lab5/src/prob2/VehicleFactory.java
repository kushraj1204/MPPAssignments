package prob2;

/**
 * @author kush
 */
public class VehicleFactory {

    public static Vehicle getVehicle(String vehicleType){
        Vehicle vehicle=null;
        if(vehicleType.equalsIgnoreCase("car")){
            vehicle=new Car();
        }
        if(vehicleType.equalsIgnoreCase("bus")){
            vehicle=new Bus();
        }
        if(vehicleType.equalsIgnoreCase("electriccar")){
            vehicle=new ElectricCar();
        }
        if(vehicleType.equalsIgnoreCase("truck")){
            vehicle=new Truck();
        }
        return vehicle;
    }
}
