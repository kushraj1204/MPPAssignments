package lesson3.labs.prob2;

/**
 * @author kush
 */
public class Summary {
    public static double calculateMonthlyProfit(LandLord landLord) {
        double totalRent = 0.0;
        double totalMaintenanceCosts = 0.0;
        for (int i = 0; i < landLord.getBuilding().size(); i++) {
            totalMaintenanceCosts = totalMaintenanceCosts + landLord.getBuilding().get(i).getMaintenanceCosts();
            for (int j = 0; j < landLord.getBuilding().get(i).getApartments().size(); j++) {
                totalRent = totalRent + landLord.getBuilding().get(i).getApartments().get(j).getRent();
            }
        }
        return totalRent - totalMaintenanceCosts;
    }
}
