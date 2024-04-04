package lesson3.labs.prob1;

/**
 * @author kush
 */
public class PersonWithJob {
    private double salary;
    private Person person;

    PersonWithJob(double salary,String name) {
        salary = salary;
        person=new Person(name);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object aPersonWithJob) {
        if(aPersonWithJob == null) return false;
        if(!(aPersonWithJob instanceof PersonWithJob)) return false;
        PersonWithJob p = (PersonWithJob)aPersonWithJob;
        boolean isEqual = this.getPerson().getName().equals(p.getPerson().getName()) &&
                this.getSalary()==p.getSalary();
        return isEqual;
    }

}
