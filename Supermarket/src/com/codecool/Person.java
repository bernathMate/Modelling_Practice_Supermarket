package com.codecool;

public class Person extends ProductStorage implements java.io.Serializable {
    
    private String fullName;
    private String gender;
    private int age;
    private PersonType personType;

    public Person(String fullName, String gender, int age, PersonType personType) {
        super();
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.personType = personType;
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

    public PersonType getPersonType() {
        return personType;
    }
}