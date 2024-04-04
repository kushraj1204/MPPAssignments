import prob2A.Student;
import prob2A.factory.StudentFactory;
import prob2B.Order;
import prob2B.OrderLine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
public class Main {
    public static void main(String[] args) {
        Student stu=new Student("Ram");
        System.out.println(stu.getGradeReport().getStudent().getName());

        Order order=new Order(LocalDate.now());
        order.addOrderLine(new OrderLine(order));
        System.out.println(order.getOrderLines().get(0).getOrder().getOrderDate());

        System.out.println(StudentFactory.createStudent("Ram").getGradeReport().getStudent().getName());;
        List<String> g=new ArrayList<>();
    }
}