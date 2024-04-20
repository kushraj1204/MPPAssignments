package prob1;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kush
 */
public class Driver {

    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("Bill", "Thomas", "Mary");
        String result = stringStream
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
