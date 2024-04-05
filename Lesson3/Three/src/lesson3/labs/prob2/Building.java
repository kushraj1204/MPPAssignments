package lesson3.labs.prob2;

import java.util.List;

/**
 * @author kush
 */
public class Building {
    private List<Apartment> apartments;
    private double maintenanceCosts;

    public Building(List<Apartment> apartmentBuilding1) {
        if (apartmentBuilding1.isEmpty()) {
            apartmentBuilding1.add(new Apartment());
        }
        this.apartments = apartmentBuilding1;
    }

    public Building(Apartment apartmentBuilding) {
        this.apartments = List.of(apartmentBuilding);
    }


    public List<Apartment> getApartments() {
        return apartments;
    }


    public double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public void setMaintenanceCosts(double maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }

    public void addApartments(List<Apartment> apartments) {
        this.apartments.addAll(apartments);
    }
}
