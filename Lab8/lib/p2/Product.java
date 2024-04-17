package p2;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Product {
	final String title;
	final double price;
	final int model;

	static enum SortMethod {
		BYNAME, BYPRICE
	}

	private SortMethod sortMethod = SortMethod.BYPRICE;

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
			return -1;
		});

	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public int getModel() {
		return model;
	}

	public Product(String title, Double price, int model) {
		this.title = title;
		this.price = price;
		this.model = model;
	}

	@Override
	public String toString() {
		return String.format("\n %s : %s : %s", title, price, model);
	}

}
