package prob2B;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate orderDate;

    private List<OrderLine> orderLines;

    public Order(LocalDate orderDate) {
        this.orderDate = orderDate;
        this.orderLines = new ArrayList<OrderLine>();
        OrderLine orderLine=new OrderLine(this);
        this.orderLines.add(orderLine);
    }
    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}
