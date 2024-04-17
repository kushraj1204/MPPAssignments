package p4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class test {
	static enum SortMethod {
		BYPRICE, BYTITLE
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] names = { "Alexis", "Tim", "Kyleen", "Kristy" };
		Arrays.sort(names, (e1, e2) -> {
			return e1.compareToIgnoreCase(e2);
		});
		Arrays.stream(names).forEach(System.out::println);
		List<String> list = Arrays.asList(names);
		
		list.forEach((e) -> {
			  System.out.print(e + ", ");
			});

	}

}
