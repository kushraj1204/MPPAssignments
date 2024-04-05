package lesson3.labs.prob1;


public class Person {
	private String name;
	Person(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object aPerson) {
		if(aPerson == null) return false; 
		if(!(aPerson instanceof Person)) return false;
		Person p = (Person)aPerson;
		boolean isEqual = this.name.equals(p.name);
		return isEqual;
	}
	public static void main(String[] args) {
		Person person=new Person("Ram");
		PersonWithJob pwj=new PersonWithJob(20000,"Shyam");
		System.out.println(pwj.equals(person));
		System.out.println(person.equals(pwj));
	}

}
