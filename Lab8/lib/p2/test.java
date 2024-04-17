package p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import p2.Product.SortMethod;

public class test {
	static enum SortMethod {
		BYPRICE, BYTITLE
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Product> emps = new ArrayList<>();
		emps.add(new Product("cellular", 1000.00, 1));
		emps.add(new Product("subscription", 5000.0, 1));
		emps.add(new Product("AND", 1000.0, 1));

		test ei = new test();
		ei.sort(emps, test.SortMethod.BYPRICE);
		System.out.println(emps);
		ei.sort(emps, test.SortMethod.BYTITLE);
		System.out.println(emps);
	}

	public void sort(List<Product> prods, SortMethod method) {

		Collections.sort(prods, (e1, e2) -> {
			if (method == SortMethod.BYPRICE) {
				if (e1.price > e2.price)
					return 1;
				else if (e1.price == e2.price)
					return 0;
				else if (e1.price < e2.price)
					return -1;
			}
			if (method == SortMethod.BYTITLE) {
				return e1.getTitle().compareTo(e2.getTitle());
			}
			return -1;
		});

	}

	public void sort2(List<Product> emps, final SortMethod method) {
		class ProductComparator implements Comparator<Product> {
			@Override
			public int compare(Product e1, Product e2) {
				if (method == SortMethod.BYTITLE) {
					return e1.getTitle().compareTo(e2.getTitle());
				}
				if (method == SortMethod.BYPRICE) {
					if (e1.price > e2.price)
						return 1;
					else if (e1.price == e2.price)
						return 0;
					else if (e1.price < e2.price)
						return -1;
				}
				return 0;
			}
		}
		Collections.sort(emps, new ProductComparator());
	}

	public void sort3(List<Product> emps, final SortMethod method) {
	
		
		Collections.sort(emps,(e1,e2)->{
			if (method == SortMethod.BYTITLE) {
				return e1.getTitle().compareTo(e2.getTitle());
			}
			if (method == SortMethod.BYPRICE) {
				if (e1.price > e2.price)
					return 1;
				else if (e1.price == e2.price)
					return 0;
				else if (e1.price < e2.price)
					return -1;
			}
			return 0;
		});
	}
}
