public class Person {
    
    private String fullName;
    private String gender;
    private int age;

    public Person(String fullName, String gender, int age) {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}