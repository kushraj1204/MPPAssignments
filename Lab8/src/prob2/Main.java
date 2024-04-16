package prob2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author kush
 */
public class Main {

    enum SortMethod {BYTITLE, BYPRICE}

    ;

    public static void main(String[] args) {
        Product p1 = new Product("Tesla", 30000.00, 3);
        Product p2 = new Product("Tesla", 53000.00, 1);
        Product p3 = new Product("Toyota", 27000.00, 2);
        Product p4 = new Product("Hyundai", 75000.00, 10);
        Product p5 = new Product("Tata", 6000.00, 5);
        Product p6 = new Product("BYD", 13000.00, 5);
        List<Product> pList = new ArrayList<>();
        pList.addAll(List.of(p1, p2, p3, p4, p5, p6));


        //a
        EmpComparatorTitle compTitle = new EmpComparatorTitle();
        Collections.sort(pList, compTitle);

        //b
        EmpComparatorPrice compPrice = new EmpComparatorPrice();
        Collections.sort(pList, compPrice);

        //c
        Main main=new Main();
        main.sort(pList,SortMethod.BYPRICE);


        //d
        Comparator<Product> comparator=(o1,o2)->{
            Integer result=o1.getTitle().compareTo(o2.getTitle());
            return result==0?Integer.compare(o1.getModel(),o2.getModel()):result;
        };

        Collections.sort(pList,comparator);




    }

    public void sort(List<Product> products, SortMethod sortMethod) {
        class ProductComparator implements Comparator<Product> {
            @Override
            public int compare(Product o1, Product o2) {
                if (sortMethod.equals(SortMethod.BYPRICE)) {
                    return o1.getTitle().compareTo(o2.getTitle());
                } else {
                    if (o1.getPrice() == o2.getPrice())
                        return 0;
                    return o1.getPrice() > o2.getPrice() ? 1 : -1;
                }
            }
        }
    }
}