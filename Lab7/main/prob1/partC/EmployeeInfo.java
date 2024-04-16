package prob1.partC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import prob1.partB.Employee;

public class EmployeeInfo {

	/**
	 * Removes all duplicate Employee instances from input list (only a copy is
	 * modified) An Employee instance is considered to be a duplicate of another
	 * Employee instance if the two instances have the same name and salary.
	 */
	public static List<Employee> removeDuplicates(List<Employee> employees) {
		HashMap<Employee, Employee> tracker = new HashMap<>();
		List<Employee> noDupsList = new ArrayList<>();
		for (int i = 0; i < employees.size(); ++i) {
			Employee e = employees.get(i);
			if (!tracker.containsKey(e)) {
				if(e.getName().equals("Richard")) {
					System.out.println("EMPLOYEE OBJECT"+employees.get(i));
					System.out.println("HASH"+e.hashCode());
					System.out.println("TRACKER"+tracker.toString());}
					
				tracker.put(e, e);
				noDupsList.add(e);
			}
			else {
				System.out.println(e);
			}
		}
		return noDupsList;
	}

	/**
	 * Tests to see if solution is correct What's wrong here?
	 */
	public static void main(String[] args) {
		List<Employee> list = new ArrayList<Employee>() {
			{
				add(new Employee("Richard", 55000));
				add(new Employee("Richard", 55000));
				add(new Employee("John", 30000));
				add(new Employee("Steve", 30000));
				add(new Employee("Zeke", 50000));
				add(new Employee("Reggie", 50000));
				add(new Employee("Steve", 30000));
				add(new Employee("John", 70000));
				add(new Employee("Harold", 55000));
				add(new Employee("Richard", 55000));
				add(new Employee("Richard", 55000));
				add(new Employee("Richard", 60000));
				add(new Employee("Dennis", 80000));
				add(new Employee("Adam", 80000));
				add(new Employee("John", 70000));
				add(new Employee("Charles", 25000));
				add(new Employee("Bill", 60000));
				add(new Employee("Bob", 60000));
				add(new Employee("Bill", 70000));
				add(new Employee("Bob", 60000));
			}
		};

		// List with duplicates removed - correctly computed
		List<Employee> dupsRemoved = new ArrayList<Employee>() {
			{
				add(new Employee("Richard", 55000));
				add(new Employee("John", 30000));
				add(new Employee("Steve", 30000));
				add(new Employee("Zeke", 50000));
				add(new Employee("Reggie", 50000));
				add(new Employee("John", 70000));
				add(new Employee("Harold", 55000));
				add(new Employee("Richard", 60000));
				add(new Employee("Dennis", 80000));
				add(new Employee("Adam", 80000));
				add(new Employee("Charles", 25000));
				add(new Employee("Bill", 60000));
				add(new Employee("Bill", 70000));
				add(new Employee("Bob", 60000));
			}
		};
		dupsRemoved.forEach(new Consumer<Employee>() {

			@Override
			public void accept(Employee e) {
				// TODO Auto-generated method stub
				System.out.println(e.toString());
			}
		});

		List<Employee> answer = removeDuplicates(list);
		System.out.println("Answer");
		answer.forEach(new Consumer<Employee>() {

			@Override
			public void accept(Employee e) {
				// TODO Auto-generated method stub
				System.out.println(e.toString());
			}
		});

		System.out.println("Is answer correct? " + listsAreEqual(answer, dupsRemoved));
	}

	/**
	 * Returns true if the two lists have the same size and contain exactly the same
	 * elements (this is really just set equality)
	 */
	public static boolean listsAreEqual(List<Employee> l1, List<Employee> l2) {
		if (l1.size() != l2.size()) {

			System.out.println("sizel1" + l1.size() + "sizel2" + l2.size());
			return false;
		}
		for (Employee e : l1) {
			if (!l2.contains(e)) {
				System.out.println(e.toString());
				return false;
			}
		}
		return true;
	}

}
