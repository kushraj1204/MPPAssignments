package prob2B;

public class OrderLine {

    private Integer orderlinenum;
    private Double price;
    private Integer quantity;
    private Order order;

    public OrderLine(Order order) {
        this.order=order;
    }
    public Order getOrder() {
        return order;
    }

}
