package prob3;

import prob1.Group;

import java.util.Arrays;
import java.util.List;

/**
 * @author kush
 */
public class Driver {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Bob", "Joe", "Tom");
        boolean result = Driver.contains1(list, "Tom");
        System.out.println(result);

        List<Integer> list1 = Arrays.asList(0,1,2,3,4);
        Group<Integer> group1 = new Group<>(0, list1);
        List<Integer> list2 = Arrays.asList(0,1,2,3,5);
        Group<Integer> group2 = new Group<>(5, list2);
        List<Integer> list3 = Arrays.asList(0,1,2,5,3);
        Group<Integer> group3 = new Group<>(5, list3);
        System.out.println(Driver.contains1(Arrays.asList(group1,group2),group3));
        System.out.println(Driver.contains1(Arrays.asList(group1,group2),group1));

        List<Integer> listInt = Arrays.asList(1,67,89,100);
        boolean resultInt = Driver.contains1(listInt, 89);
        System.out.println(result);

    }

    public static<T> boolean contains1(List<T> list, T s) {
        for (T x : list) {
            if (x == null && s == null) {
                return true;
            }
            if (s == null || x == null) {
                return false;
            }
            if (x.equals(s)) return true;
        }
        return false;
    }
}
