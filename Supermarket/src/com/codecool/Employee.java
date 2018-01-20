package com.codecool;

public class Employee extends Person {
    
    private String position;

    public Employee(String fullName, String gender, int age, String position, PersonType personType) {
        super(fullName, gender, age, personType);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Name= " + getFullName()
                + ", Gender= " + getGender()
                + ", Age= " + getAge()
                + ", Position= " + getPosition()
                + ", Person's type= " + getPersonType();
    }
}