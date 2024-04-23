package prob2;

import java.util.ArrayList;
import java.util.List;

public class mininList {

	public static void main(String args[]) {
		List <Integer > test = new ArrayList<Integer>();
		test.add(3);
		test.add(1);
		test.add(-1);
		System.out.print(mininlist(test));
		System.out.print(false);
	}

	public static <T extends Comparable<T>> T mininlist(List<T> list) {
		T min = list.get(0);
		for(T i : list) {
			if(i.compareTo(min)<0){
				min = i;
			}
		}
		
		return min; 
	}
}
