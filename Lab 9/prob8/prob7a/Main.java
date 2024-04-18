package prob7a;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(new Employee("Joe", "Davis", 120000), new Employee("John", "Sims", 110000),
				new Employee("Joe", "Stevens", 200000), new Employee("Andrew", "Reardon", 80000),
				new Employee("Joe", "Cummings", 760000), new Employee("Steven", "Walters", 135000),
				new Employee("Thomas", "Blake", 111000), new Employee("Alice", "Richards", 101000),
				new Employee("Donald", "Trump", 100000));

		// your stream pipeline here

		list.stream().filter(i -> i.getSalary() > 100000).filter(i -> i.getLastName().startsWith("N")
				|| i.getLastName().startsWith("O") || i.getLastName().startsWith("P") || i.getLastName().startsWith("Q")
				|| i.getLastName().startsWith("R") || i.getLastName().startsWith("S") || i.getLastName().startsWith("T")
				|| i.getLastName().startsWith("U") || i.getLastName().startsWith("V") || i.getLastName().startsWith("X")
				|| i.getLastName().startsWith("Y") || i.getLastName().startsWith("W")
				|| i.getLastName().startsWith("Z")).sorted(Comparator.comparing(Employee::getFirstName))
				.forEach(i -> System.out.print(i.getFirstName() + " " + i.getLastName() + ","));

		list.stream().filter(i -> i.getSalary() > 100000).filter(i -> i.getLastName().charAt(0) == -1)
				.sorted(Comparator.comparing(Employee::getFirstName))
				.forEach(i -> System.out.print(i.getFirstName() + " " + i.getLastName() + ","));

		//System.out.println(list);

		List<Employee> list2 = LambdaLibrary.ORDER.apply(list);
		System.out.println();
		System.out.println(list2);
	}

}
