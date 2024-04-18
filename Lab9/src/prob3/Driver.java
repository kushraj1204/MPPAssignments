package prob3;

import java.util.Arrays;
import java.util.List;

/**
 * @author kush
 */
/*Implement a given method requirement using Lambdas and streams in a Java 8 way.
public int countWords(List<String> words, char c, char d, int len)
which counts the number of words in the input list words that have length equal to len, that
contain the character c, and that do not contain the character d.*/
public class Driver {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("RAM", "SHYAM", "MICHAEL", "HARRY", "ADELE", "ELON");
        int count = countWords(words, 'C', 'D', 7);
        System.out.println(count);
    }

    public static int countWords(List<String> words, char c, char d, int len) {
        return (int) words.stream()
                .filter(x -> x.length() == len)
                .filter(x -> x.contains(String.valueOf(c)))
                .filter(x -> !x.contains(String.valueOf(d)))
                .count();
    }
}
