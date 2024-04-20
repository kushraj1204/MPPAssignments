package prob2;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kush
 */
public class Driver {
/*2. You have a Stream of Integers called myIntStream and you need to output both the
    maximum and minimum values. Write compact code that efficiently accomplishes this.*/
    public static void main(String[] args) {
        Stream<Integer> myIntStream=Stream.of(3,65,87,1,67,436,222,0);
        IntSummaryStatistics stat=myIntStream.collect(Collectors.summarizingInt(x->x));
        System.out.println("Max value is:"+stat.getMax()+" and Min value is: "+stat.getMin());
    }
}
