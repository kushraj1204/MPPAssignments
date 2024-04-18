package prob4;

import java.util.stream.Stream;

/**
 * @author kush
 */
/*public static void printSquares(int num)
which creates an IntStream using the iterate method. The method prints to the console the first
num squares. For instance, if num = 4, then your method would output 1, 4, 9, 16. Note: You
will need to come up with a function to be used in the second argument of iterate.*/
public class Driver {

    public static void main(String[] args) {
        printSquares(5);
    }
    public static void printSquares(int num){
        Stream.iterate(1,x->x+1)
                .limit(num)
                .map(x->x*x)
                .forEach(System.out::println);
    }
}
