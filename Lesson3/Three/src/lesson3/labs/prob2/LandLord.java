package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
public class LandLord {
    private List<Building> building;

    public LandLord() {
        this.building=new ArrayList<>();
    }

    public List<Building> getBuilding() {
        return building;
    }

    public void setBuilding(List<Building> building) {
        this.building = building;
    }
    public void addBuilding(List<Building> buildings) {
        List<Building> bldgs=new ArrayList<>();
        bldgs.addAll(bldgs);
        bldgs.addAll(this.getBuilding());
        this.setBuilding(bldgs);
    }
}
