public class Employee extends Person {
    
    private String position;

    public Employee(String fullName, String gender, int age, String position) {
        super(fullName, gender, age);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}