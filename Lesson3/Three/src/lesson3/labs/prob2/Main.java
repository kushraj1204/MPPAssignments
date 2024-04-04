package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        LandLord landLord=new LandLord();
        List<Apartment> apartmentBuilding1=new ArrayList<>();
        Apartment apartment1Building1=new Apartment();
        apartment1Building1.setRent(7000);
        apartmentBuilding1.add(apartment1Building1);

        List<Building> buildings=new ArrayList<>();
        Building building1=new Building();
        building1.addApartments(apartmentBuilding1);
        building1.setMaintenanceCosts(2000);
        buildings.add(building1);

        landLord.setBuilding(buildings);
        System.out.println(Summary.calculateMonthlyProfit(landLord));
    }

}
