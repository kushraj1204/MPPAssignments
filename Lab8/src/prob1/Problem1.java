package prob1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Problem1 {

    public static void main(String[] args) {
        // To understand functions as First class citizen

        // Task 1: Identify the suitable interface to read the input and print doubled
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Consumer<Integer> printer = x -> System.out.println(2 * x);
        changeDouble(numbers, printer);// Output: 2 4 6 8 10


        //Task 2: Take input of String and transform into Upper case
        Function<String, String> upperCaseConverter = x -> x.toUpperCase();
        List<String> data = Arrays.asList("apple", "banana", "cherry");
        transformStrings(data, upperCaseConverter);


        // Task 3: Check the inputs of given value is divisible by 5.
        IntPredicate isDivBy5 = x -> x % 5 == 0;
        List<Integer> inputs = Arrays.asList(10, 21, 12, 25, 33);
        printIf(inputs, isDivBy5);
    }

    public static void changeDouble(List<Integer> numbers, Consumer<Integer> printer) {
        for (Integer number : numbers) {
            printer.accept(number);
        }
    }

    public static void transformStrings(List<String> list, Function<String, String> converter) {
        for (String s : list) {
            System.out.println(converter.apply(s));
        }
    }

    public static void printIf(List<Integer> numbers,IntPredicate isDivBy5) {
        for (Integer number : numbers) {
            if (isDivBy5.test(number)) {
                System.out.println(number);
            }
        }
    }
}

