import java.util.List;

/**
 * @author kush
 */
public class Commissioned extends Employee {
    private double commission;
    private double baseSalary;

    private List<Order> orderList;

    public Commissioned(String empId,double commission, double baseSalary, List<Order> orderList) {
        super(empId);
        this.commission = commission;
        this.baseSalary = baseSalary;
        this.orderList = orderList;
    }

    @Override
    double calcGrossPay(int month, int year) {
        int prevMonth;
        int yearOfPrevMonth;

        if (month == 1) {
            prevMonth = 12;
            yearOfPrevMonth = year - 1;
        } else {
            prevMonth = month - 1;
            yearOfPrevMonth = year;
        }
        double orderAmtPrevMonth=getOrderAmt(prevMonth,yearOfPrevMonth);
        return getBaseSalary()+(getCommission()/100)*orderAmtPrevMonth;
    }

    private double getOrderAmt(int month, int year) {
        double orderAmt=0.0;
        List<Order> orders=getOrderList();
        for (Order order : orders) {
            if (order.getOrderDate().getYear() == year && order.getOrderDate().getMonthValue() == month) {
                orderAmt = orderAmt + order.getOrderAmount();
            }
        }
        return  orderAmt;
    }

    public double getCommission() {
        return commission;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
