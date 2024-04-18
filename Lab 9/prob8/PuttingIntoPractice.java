import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class PuttingIntoPractice {
	public static void main(String... args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		// Query 1: Find all transactions from year 2011 and sort them by value (small
		// to high).

		transactions.stream().filter(e -> e.getYear() >= 2011).sorted(Comparator.comparing(Transaction::getValue))
				.forEach(e -> System.out.println(e.toString()));
		;

		// Query 2: What are all the unique cities where the traders work?
		System.out.println("____");
		transactions.stream().filter(distinctByKey(e -> e.getTrader().getCity()))
				.forEach(i -> System.out.println(i.getTrader().getCity()));

		// Query 3: Find all traders from Cambridge and sort them by name.
		Function<Transaction, String> byname = e -> e.getTrader().getName();
		transactions.stream().filter(e -> e.getTrader().getCity().equals("Cambridge"))
				.sorted(Comparator.comparing(byname)).forEach(e -> System.out.println(e.toString()));

		// Query 4: Return a string of all traders names sorted alphabetically.
		System.out.println("____NAME");

		transactions.stream().sorted(Comparator.comparing(byname)).forEach(System.out::println);
		// Query 5: Are there any trader based in Milan?
		System.out.println("____CITY MILAN");
		transactions.stream().filter(e->e.getTrader().getCity().equals("Milan")).forEach(System.out::println);;
		// Query 6: Update all transactions so that the traders from Milan are set to
		// Cambridge.
		//transactions.stream().filter(e->e.getTrader().getCity().equals("Milan")).forEach(f->f.getTrader().setCity("Cambridge").forEach(System.out::println);
		System.out.println("____CITY MILAN2aa");

		transactions.stream().filter(e->e.getTrader().getCity().equals("Milan")).forEach(e->e.getTrader().setCity("aa"));
		transactions.stream().forEach(System.out::println);
		
		// Query 7: What's the highest value in all the transactions?
		
		System.out.println("COMPARATORRRR mAX"+transactions.stream().max(Comparator.comparing(Transaction::getValue)));
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}
}
