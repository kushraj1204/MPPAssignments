package prob9;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function<List<Dish> , Boolean> functionvegetarian = f->f.stream().filter(e->e.isVegetarian()).count()>0;
		System.out.println("test vegetarian"+functionvegetarian.apply(Dish.menu));
		//Dish.menu.stream().filter(e->e.isVegetarian()).count();
		
		Function<List<Dish> , Boolean> functioncalorieshealthy = f->f.stream().filter(e->e.getCalories()<1000).count()>0;
		System.out.println("test calories healthy"+functioncalorieshealthy.apply(Dish.menu));

		Function<List<Dish> , Boolean> functioncaloriesunhealthy = f->f.stream().filter(e->e.getCalories()<1000).count()>0;
		System.out.println("test calories unhealthy"+functioncaloriesunhealthy.apply(Dish.menu));
		
		Function<List<Dish> , Optional<Dish>> meatfirstitem = f->f.stream().filter(e->e.getType().equals(Dish.Type.MEAT)).findFirst();
		System.out.println("first meat"+meatfirstitem.apply(Dish.menu));
		
		long sum = Dish.menu.stream().map(e -> e.getCalories()).reduce((int) 0L, Integer::sum);
		System.out.println("total calories"+sum);
		
		
		long sum2 = Dish.menu.stream().map(Dish::getCalories).reduce((int) 0L, Integer::sum);
		System.out.println("total calories method reference"+sum2);
	}

}
