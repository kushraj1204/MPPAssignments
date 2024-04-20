package prob6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author kush
 */
public class Driver {
    /*    Implement a smethod
        public Set<String> union(List<Set<String>> sets)
        by creating a stream pipeline that transforms a list of sets (of type String) into the union
        of those sets. Make use of the reduce method for streams.
                Example: The union method should transform the list [{“A”, “B”}, {“D”}, {“1”, “3”,
    “5”}] to the set {“A”, “B”, “D”, “1”, “3”, “5”}.*/
    public static void main(String[] args) {
        HashSet<String> set1 = new HashSet<>(List.of("A", "B"));
        HashSet<String> set2 = new HashSet<>(List.of("D"));
        HashSet<String> set3 = new HashSet<>(List.of("1", "3", "5"));
        Set<String> result = union(List.of(set1, set2, set3));
        System.out.println(result.toString());
    }

    public static Set<String> union(List<Set<String>> sets) {
        return sets
                .stream()
                .reduce(new HashSet<String>(), (x, y) -> {
                    x.addAll(y);
                    return x;
                });

    }
}
