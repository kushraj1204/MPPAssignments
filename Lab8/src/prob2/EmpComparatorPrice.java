package prob2;

import java.util.Comparator;

/**
 * @author kush
 */
public class EmpComparatorPrice implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getPrice()==o2.getPrice())
            return 0;
        return o1.getPrice() > o2.getPrice() ? 1 : -1;
    }
}
