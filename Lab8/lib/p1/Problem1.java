package p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Problem1 {

	public static void main(String[] args) {
		// To understand functions as First class citizen
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		Consumer<Integer> c = x -> System.out.println(2 * x);
		// Task 1: Ident ify the suitable interface to read the input and print doubled
		// changeDouble(numbers /* , Functional Interface implementation */ );// Output:
		// 2 4 6 8 10
		changeDouble(numbers, c);// Output: 2 4 6 8 10
		BiFunction<Double, Double, Double> milestokm = (mile, km) -> mile * km;

		List<String> data = Arrays.asList("apple", "banana", "cherry");
		// Task 2: Take input of String and transform into Upper case
		Consumer<String> d = x -> System.out.println(x.toUpperCase());
		transformStrings(data, d);

		// Task 3: Check the inputs of given value is divisible by 5.
		List<Integer> inputs = Arrays.asList(10, 21, 12, 25, 33);
		Function<Integer, Boolean> fid = x -> (x / 5) % 5 == 0;
		printIf(inputs, fid);
	}

	public static void changeDouble(List<Integer> numbers, Consumer<Integer> c) {
		for (Integer number : numbers) {
			// action.accept(number);
			c.accept(number);
		}
	}

	public static void transformStrings(List<String> list, Consumer<String> d) {
		for (String s : list) {
			// System.out.println(transformer.apply(s));
			d.accept(s);
		}
	}

	public static void printIf(List<Integer> numbers, Function<Integer, Boolean> fid) {
		for (Integer number : numbers) {

			if (fid.apply(number)) {
				System.out.println(number);
			}

		}
	}
}
