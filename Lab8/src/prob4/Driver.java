package prob4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author kush
 */
public class Driver {

/*    String[] names = {"Alexis", "Tim", "Kyleen", "Kristy"};
    a. Use Arrays.sort() to sort the names by ignore case using Method reference.
    b. Convert the sorted names array into List.
    c. Print the sorted list using method reference.*/

    public static void main(String[] args) {
        String[] names = {"Alexis", "Tim", "Kyleen", "Kristy"};
        Consumer<String[]> con=x->Arrays.sort(x,String::compareToIgnoreCase);
        con.accept(names);

        Function<String[], List<String>> arrToList= Arrays::asList;
        List<String> list=arrToList.apply(names);

        Consumer<List<String>> printer= System.out::println;

        printer.accept(list);


    }
}
