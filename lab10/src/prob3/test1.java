package prob3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class test1 {

	public static void main(String args[]) {

		List<String> list = Arrays.asList("Bob", "Joe", "tom","Ana");
		System.out.println(test1.contains(list, "Ana"));
	}

	private static <T> boolean contains(List<T> list, T check) {
		// TODO Auto-generated method stub
		for (T i : list) {
			if (i == null && check == null)
				return true;
			if (i.equals(check))
				return true;
		}
		return false;
	}
}
