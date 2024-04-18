package prob7a;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaLibrary {

	// Illustrates a workaround for limitation on stream reuse: Make the stream
	// part of a larger lambda expression implementing some type of Function
	// interface,
	// and then gain access to the desired stream with a call to the Function's
	// apply method
	public static final Function<List<Employee>, List<Employee>> ORDER = (list) -> list.stream()
			.filter(i -> i.getSalary() > 100000)
			.filter(i -> i.getLastName().startsWith("N") || i.getLastName().startsWith("O")
					|| i.getLastName().startsWith("P") || i.getLastName().startsWith("Q")
					|| i.getLastName().startsWith("R") || i.getLastName().startsWith("S")
					|| i.getLastName().startsWith("T") || i.getLastName().startsWith("U")
					|| i.getLastName().startsWith("V") || i.getLastName().startsWith("X")
					|| i.getLastName().startsWith("Y") || i.getLastName().startsWith("W")
					|| i.getLastName().startsWith("Z"))
			.sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());

}
