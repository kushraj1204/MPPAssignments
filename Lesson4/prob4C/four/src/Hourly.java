/**
 * @author kush
 */
public class Hourly extends Employee {
    private double hourlyWage;
    private int hoursPerWeek;

    private final int WEEKS_PER_MONTH = 4;

    public Hourly(String empId, double hourlyWage, int hoursPerWeek) {
        super(empId);
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    @Override
    public double calcGrossPay(int month, int year) {
        return getHourlyWage() * getHoursPerWeek() * WEEKS_PER_MONTH;
    }
}
