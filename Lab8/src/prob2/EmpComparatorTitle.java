package prob2;

import java.util.Comparator;

/**
 * @author kush
 */


public class EmpComparatorTitle implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}