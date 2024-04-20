package prob7b;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kush
 */
public class LambdaLibrary {
    public static final QuadruFunction<List<Employee>, Integer, String, String,
            String> NAMES_ABOVE_SALARY_AND_SURNAME_BETWEEN = (list, salary, from, to) ->
            list
                    .stream()
                    .filter(x -> x.getSalary() > salary)
                    .filter(x -> x.getLastName().compareToIgnoreCase(from) >= 0
                            && x.getLastName().compareToIgnoreCase(to) <= 0)
                    .map(x -> x.getFirstName() + " " + x.getLastName())
                    .sorted()
                    .collect(Collectors.joining(", "));
}
