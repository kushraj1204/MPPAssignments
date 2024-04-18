package prob9;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {MEAT, FISH, OTHER}

    @Override
    public String toString() {
        return name;
    }

    public static final List<Dish> menu =
            Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
                    new Dish("beef", false, 700, Type.MEAT),
                    new Dish("chicken", false, 400, Type.MEAT),
                    new Dish("french fries", true, 530, Type.OTHER),
                    new Dish("rice", true, 350, Type.OTHER),
                    new Dish("season fruit", true, 120, Type.OTHER),
                    new Dish("pizza", true, 550, Type.OTHER),
                    new Dish("prawns", false, 400, Type.FISH),
                    new Dish("salmon", false, 450, Type.FISH));

    public static void main(String[] args) {
        boolean anyVegetarianAvailable = anyVegetarianAvailable();
        System.out.println("Any Vegetarian Available: " + anyVegetarianAvailable);
        boolean healthyMenuAvailable = healthyMenuAvailable();
        System.out.println("Healthy Menu Available: " + healthyMenuAvailable);
        boolean unhealthyMenuAvailable = unhealthyMenuAvailable();
        System.out.println("Unhealthy Menu Available: " + unhealthyMenuAvailable);
        Optional<Dish> dishOpt = firstMeatItem();
        System.out.println("First Meat item is: " + (dishOpt.isPresent() ? dishOpt.get().getName() : "None"));
        int totalCalories = calculateTotalCalories();
        System.out.println("Total calories count is "+totalCalories);

    }

    private static boolean anyVegetarianAvailable() {
        return menu.stream()
                .anyMatch(Dish::isVegetarian);
    }

    private static boolean healthyMenuAvailable() {
        return menu.stream()
                .filter(x -> x.getCalories() < 1000)
                .findAny()
                .isPresent();
    }

    private static boolean unhealthyMenuAvailable() {
        return menu.stream()
                .filter(x -> x.getCalories() > 1000)
                .findAny()
                .isPresent();
    }

    private static Optional<Dish> firstMeatItem() {
        return menu.stream()
                .filter(x -> x.getType().equals(Type.MEAT))
                .findFirst();
    }

    private static int calculateTotalCalories() {
        List<Integer> calories = menu.stream()
                .map(x -> x.getCalories())
                .collect(Collectors.toList())
                ;
        return 0;
    }
}



/*
9. In prob9 folder you have a one class called Dish and it has a List menu. In that class
implement some static methods to decide the following with help of Optional, anyMatch(),
allMatch(), noneMatch(),findAny(), findFirst() operations from stream.
a. Is there any Vegetarian meal available ( return type boolean)
b. Is there any healthy menu have calories less than 1000 ( return type boolean)
c. Is there any unhealthy menu have calories greater than 1000 ( return type boolean)
d. find and return the first item for the type of MEAT( return type Optional<Dish>)
e. calculateTotalCalories() in the menu using reduce. (return int)
        f. calculateTotalCaloriesMethodReference()in the menu using MethodReferences. (return int)
e. Implement a main method to test.*/
