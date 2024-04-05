package lesson3.labs.prob2;

import java.util.List;

/**
 * @author kush
 */
public class LandLord {
    private List<Building> building;

    public LandLord(List<Building> building) {
        if(building.isEmpty()){
            building.add(new Building(new Apartment()));
        }
        this.building = building;
    }

    public List<Building> getBuilding() {
        return building;
    }

    public void addBuilding(List<Building> buildings) {
        this.building.addAll(buildings);
    }
}
