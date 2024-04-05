package prob3.extpackage;

import prob3.CustOrderFactory;
import prob3.Customer;
import prob3.Order;

import java.time.LocalDate;


public class Main {
	public static void main(String[] args) {
		Customer cust= CustOrderFactory.createCustomer("Bob");
		Order order=CustOrderFactory.addOrder(cust,LocalDate.now().minusDays(1));
		CustOrderFactory.addItemToOrder(order,"Shirt");
		CustOrderFactory.addItemToOrder(order,"Laptop");

		Order order1=CustOrderFactory.addOrder(cust,LocalDate.now());
		CustOrderFactory.addItemToOrder(order1,"Pants");
		CustOrderFactory.addItemToOrder(order1,"Knife Set");

		System.out.println(cust.getOrders());

	}
}

		
