package prob3;

import java.time.LocalDate;

/**
 * @author kush
 */
public class CustOrderFactory {

    public static Customer createCustomer(String name) {
        Customer cust = new Customer(name);
        return cust;
    }

    public static Order addOrder(Customer customer, LocalDate localDate) {
        if (customer == null)
            throw new NullPointerException("Customer cannot be null");
        Order order = new Order(localDate);
        customer.addOrder(order);
        return order;
    }

    public static Item addItemToOrder(Order order, String itemName) {
        if (order == null)
            throw new NullPointerException("Order cannt be null");
        Item item = new Item(itemName);
        order.addItem(item);
        return item;
    }

}
