package prob8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );


        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        System.out.println("\n1.");
        List<Transaction> txn2011=transactions
                .stream()
                .filter(x->x.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println("All transactions from year 2011 and sorted by value");
        txn2011.stream().forEach(x-> System.out.println(x.toString()));
        System.out.println("\n");



        // Query 2: What are all the unique cities where the traders work?
        List<String> uniqueCities=transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("All the unique cities where the traders work");
        uniqueCities.stream().forEach(System.out::println);

        System.out.println("\n");

        // Query 3: Find all traders from Cambridge and sort them by name.

        List<Trader> tradersFromCambride=transactions
                .stream()
                .map(x->x.getTrader())
                .filter(x->x.getCity().equalsIgnoreCase("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("All traders from Cambridge and sorted by name");
        tradersFromCambride.stream().forEach(x-> System.out.println(x.toString()));

        System.out.println("\n");
        // Query 4: Return a string of all traders names sorted alphabetically.

        List<String> allTraders=transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("All traders names sorted alphabetically");
        allTraders.stream().forEach(System.out::println);

        System.out.println("\n");
        // Query 5: Are there any trader based in Milan?

        boolean traderInMilan=transactions
                .stream()
                .map(x->x.getTrader())
                .filter(x->x.getCity().equalsIgnoreCase("Milan"))
                .findAny()
                .isPresent();
        System.out.println("Any traders based in Milan? "+traderInMilan);

        System.out.println("\n");
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.

        List<Transaction> updatedTxns=transactions
                .stream()
                .map(x->{
                    if(x.getTrader().getCity().equalsIgnoreCase("Milan")){
                        x.getTrader().setCity("Cambridge");
                    }
                    return x;
                })
                .collect(Collectors.toList());
        System.out.println("All transactions so that the traders from Milan are set to Cambridge");
        updatedTxns.stream().forEach(x-> System.out.println(x.toString()));


        System.out.println("\n");
        // Query 7: What's the highest value in all the transactions?

        int highestTxnValue=transactions
                .stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();


        System.out.println("\n");

    }
}
