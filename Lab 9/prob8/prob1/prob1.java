package prob1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class prob1 {

	public static void main(String args[]) {
		/*
		 * 1. You have a Stream of Strings called stringStream consisting of the values
		 * “Bill”, “Thomas”, and “Mary”. Write the one line of code necessary to print
		 * this stream to the console so that the output looks like this: Bill, Thomas,
		 * Mary
		 */
		Stream<String> stringStream = Stream.of("Bill", "Thomas", "Mary");
		stringStream.forEach((e) -> {
			System.out.print(e + ",");
		});

		/*
		 * 2. You have a Stream of Integers called myIntStream and you need to output
		 * both the maximum and minimum values. Write compact code that efficiently
		 * accomplishes this.
		 */
		Integer[] ac = { 22, 229, 44, 99, 55, 11, 110 };

		System.out.print("Máx:" + Arrays.stream(ac).mapToInt(x -> x).max().getAsInt());
		System.out.print("Min:" + Arrays.stream(ac).mapToInt(x -> x).min().getAsInt());

		/*
		 * 3. Implement a given method requirement using Lambdas and streams in a Java 8
		 * way. public int countWords(List<String> words, char c, char d, int len) which
		 * counts the number of words in the input list words that have length equal to
		 * len, that contain the character c, and that do not contain the character d.
		 */
		List<String> strlist = new ArrayList<String>();
		strlist.add("Test");
		strlist.add("Oklahoma");
		strlist.add("Iowa");
		String[] str = { "abcde" };
		System.out.println(countWords(str, 'o', 'a', 5));

		/*
		 * 4. Implement a method public static void printSquares(int num) which creates
		 * an IntStream using the iterate method. The method prints to the console the
		 * first num squares. For instance, if num = 4, then your method would output 1,
		 * 4, 9, 16. Note: You will need to come up with a function to be used in the
		 * second argument of iterate.
		 */
		printSquares(10);

		/*
		 * 5. Create a method Stream<String> streamSection(Stream<String> stream, int m,
		 * int n) which extracts a substream from the input stream stream consisting of
		 * all elements from position m to position n, inclusive; you must use only
		 * Stream operations to do this. You can assume 0 <= m <= n.
		 */

		// support method for the main method -- for testing
		Stream<String> stringStream2 = Stream.of("Bill", "Thomas", "Mary");
		Stream<String> strreturn = streamSection(stringStream2, 1, 1);
		strreturn.forEach(System.out::println);

		/*
		 * 6. Implement a method public Set<String> union(List<Set<String>> sets) by
		 * creating a stream pipeline that transforms a list of sets (of type String)
		 * into the union of those sets. Make use of the reduce method for streams.
		 * Example: The union method should transform the list [{“A”, “B”}, {“D”}, {“1”,
		 * “3”, “5”}] to the set {“A”, “B”, “D”, “1”, “3”, “5”}.
		 */
		Set<String> setA = new HashSet<>(Arrays.asList("A", "B"));
		Set<String> setB = new HashSet<>(Arrays.asList("D"));
		Set<String> setC = new HashSet<>(Arrays.asList("1", "3", "5"));

		//Set<String> union = (Set<String>) Stream.concat(setA.stream(), setB.stream());
		Stream<String> union = Stream.concat(Stream.concat( setA.stream(), setB.stream()),setC.stream());
		union.forEach(System.out::println);
	}

	public static void printSquares(int num) {

		Stream<Double> randoms = Stream.generate(Math::random);

		Stream.iterate(1.0, i -> i + 1).map(i -> i * i).limit(num).forEach(System.out::println);

	}

	public static Stream<String> streamSection(Stream<String> stream, int m, int n) {
		Stream<String> aa = stream.skip(m).limit(n);
		return aa;
		// Implement the code

	}

	public static long countWords(String[] str, char c, char d, int len) {
		// System.out.println("Array Lenght" + Arrays.stream(str).filter(f -> f.length()
		// == len)
		// .filter(f -> f.contains("" + c)).filter(f -> !f.contains("d")).count());
		// return Arrays.stream(str).filter(f->f.length()==len).count());

		return Arrays.stream(str).filter(f -> f.length() == len).filter(f -> f.contains("" + c))
				.filter(f -> !f.contains("d")).count();
	}

}
