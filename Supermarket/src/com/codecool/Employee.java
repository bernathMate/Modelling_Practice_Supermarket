public class Employee extends Person {
    
    private String position;

    public Employee(String fullName, String gender, int age, String position, PersonType personType) {
        super(fullName, gender, age personType);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}