import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
public class Driver {
    public static void main(String[] args) {
        List<Order> list = new ArrayList();
        list.add(new Order("100", LocalDate.of(2024, 3, 1),2000));
        list.add(new Order("103", LocalDate.of(2024, 4, 1),2000));
        list.add(new Order("102",LocalDate.of(2024, 3, 10),100));
        Commissioned cm = new Commissioned("123",0.8,500,list);
        Employee[] emp = { new Salaried("121",4000), new Hourly("122",15.67,20),cm};
        for(Employee e :emp){
            e.print(4,2024);
        }
        commissionedCheck();
    }

    public static void commissionedCheck(){
        List<Order> list = new ArrayList();
        list.add(new Order("100", LocalDate.of(2024, 3, 1),2000));
        list.add(new Order("103", LocalDate.of(2024, 4, 1),2000));
        list.add(new Order("102",LocalDate.of(2024, 3, 10),100));
        Employee cm = new Commissioned("123",0.8,500,list);
        PayCheck paycheck=cm.calcCompensation(4,2024);
        System.out.println(paycheck);
    }
}