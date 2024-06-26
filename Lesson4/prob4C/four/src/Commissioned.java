import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
public class Commissioned extends Employee {
    private double commission;
    private double baseSalary;

    private List<Order> orderList;

    public Commissioned(String empId, double commission, double baseSalary, List<Order> orderList) {
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
        double orderAmtPrevMonth = getOrderAmt(prevMonth, yearOfPrevMonth);
        double baseSalary = getBaseSalary();
        double commissionAmt = getCommissionAmt(orderAmtPrevMonth);
        return getGrossPay(baseSalary, commissionAmt);
    }

    private double getCommissionAmt(double orderAmtPrevMonth) {
        return (getCommission() / 100) * orderAmtPrevMonth;
    }

    private double getGrossPay(double baseSalary, double commissionAmt) {
        return baseSalary + commissionAmt;
    }

    private double getOrderAmt(int month, int year) {
        double orderAmt = 0.0;
        List<Order> orders = getOrderList();
        List<Order> payableOrders = getPayableOrders(orders, month, year);
        for (Order order : payableOrders) {
            orderAmt = orderAmt + order.getOrderAmount();

        }
        return orderAmt;
    }

    private List<Order> getPayableOrders(List<Order> orders, int month, int year) {
        List<Order> payableOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderDate().getYear() == year && order.getOrderDate().getMonthValue() == month) {
                payableOrders.add(order);
            }
        }
        return payableOrders;
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
