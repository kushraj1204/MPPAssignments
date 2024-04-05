import java.time.LocalDate;

/**
 * @author kush
 */
public abstract class Employee {

    //tax per unit: Given Rate/100
    private final double FICA_RATE = 0.23;
    private final double STATE_TAX_RATE = 0.05;
    private final double LOCAL_TAX_RATE = 0.01;
    private final double MEDICARE_RATE = 0.03;
    private final double SOCIAL_SECURITY_RATE = 0.075;
    private String empId;

    public Employee(String empId) {
        this.empId = empId;
    }


    public PayCheck calcCompensation(int month, int year) {
        double payAmount = calcGrossPay(month, year);
        PayCheck payCheck = new PayCheck(payAmount,
                FICA_RATE * payAmount,
                STATE_TAX_RATE * payAmount,
                LOCAL_TAX_RATE * payAmount,
                MEDICARE_RATE * payAmount,
                SOCIAL_SECURITY_RATE * payAmount);
        return payCheck;
    }

    public void print(int month, int year) {
        PayCheck pc=calcCompensation(month, year);
        System.out.println("Employee with ID:"+this.empId+" payment for "+month+"/"+year+" Net Amount: "+pc.getNetPay()+" Details: "+pc.toString());
    }

    abstract double calcGrossPay(int month, int year);
}
