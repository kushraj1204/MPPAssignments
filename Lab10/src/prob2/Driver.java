package prob2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kush
 */
/*Create a generic programming solution to the problem of finding the second
smallest element in a list. In other words, devise a public static method
secondSmallest so that it can handle the biggest possible range of types.*/
public class Driver {

    public static void main(String[] args) {
        List<Integer> intList=new ArrayList<>();
        intList.addAll(List.of(5, 83, 0, 56, 3, 8, 100));
        System.out.println("Second Smallest is " + secondSmallest(intList));

        List<String> strList=new ArrayList<>();
        strList.addAll(List.of("Aristotle","Michael","Robin","Bill"));
        System.out.println("Second Smallest is " + secondSmallest(strList));



    }

    public static <T extends Comparable<T>> T secondSmallest(List<T> list) {
        if (list.size() < 2) {
            return null;
        }
        Comparator<T> comp = (x, y) -> x.compareTo(y);
        List<T> result = list.stream()
                .sorted(comp)
                .distinct()
                .collect(Collectors.toList());
        if (result.size() < 2) {
            return null;
        }
        return result.get(1);
    }


}
