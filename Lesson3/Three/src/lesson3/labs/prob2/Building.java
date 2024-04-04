package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
public class Building {
    private List<Apartment> apartments;
    private double maintenanceCosts;

    public Building() {
        apartments=new ArrayList<>();
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public void setMaintenanceCosts(double maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }
    public void addApartments(List<Apartment> apartments) {
        List<Apartment> apts=new ArrayList<>();
        apts.addAll(apartments);
        apts.addAll(this.getApartments());
        this.setApartments(apts);
    }
}
