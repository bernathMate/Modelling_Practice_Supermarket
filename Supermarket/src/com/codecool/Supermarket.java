package com.codecool;

import java.util.*;
import java.io.*;

public class Supermarket extends ProductStorage {

    private String name;
    private Person[] persons;

    public Supermarket(String name) {
        super();
        this.name = name;
        persons = new Person[0];
    }

    public void init() {
        System.out.println("Welcome in the " + getName() + " supermarket!");
    }

    public String getName() {
        return name;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void addToPerson(Person person) {
        Person[] tempArray = new Person[persons.length + 1];
        for(int i = 0; i < persons.length; i++) {
            tempArray[i] = persons[i];
        }
        tempArray[tempArray.length - 1] = person;
        persons = tempArray;
    }

    public Customer createCustomer(PersonType personType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is her/his full name?");
        String customerFullName = sc.nextLine();
        System.out.println("What is her/his gender?");
        String customerGender = sc.nextLine();
        System.out.println("How old is she/he?");
        int customerAge = sc.nextInt();
        System.out.println("How much money has she/he got?");
        int customerMoney = sc.nextInt();
        Customer person = new Customer(customerFullName, customerGender, customerAge, customerMoney, personType);
        return person;
    }

    public Employee createEmployee(PersonType personType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is her/his full name?");
        String employeeFullName = sc.nextLine();
        System.out.println("What is her/his gender?");
        String employeeGender = sc.nextLine();
        System.out.println("How old is she/he?");
        int employeeAge = sc.nextInt();
        System.out.println("What is her/his position?");
        sc.nextLine();
        String employeePosition = sc.nextLine();
        Employee person = new Employee(employeeFullName, employeeGender, employeeAge, employeePosition, personType);
        return person;
    }

    public void createPerson(PersonType personType) {
        Person person;
        switch(personType) {
            case CUSTOMER:
                person = createCustomer(personType);
                break;
            case EMPLOYEE:
                person = createEmployee(personType);
                break;
            default:
                throw new IllegalArgumentException("This person type is invalid!");
        }
        addToPerson(person);
    }

    public void uploadProducts(String csvFile) {
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader("../data/"+csvFile))){
            while((line = br.readLine()) != null){
                String[] product = line.split(",");
                if (product.length == 4) {
                    NonEdibleProduct nonEdibleProduct = new NonEdibleProduct(product[0], Integer.parseInt(product[1]), Integer.parseInt(product[2]), product[3]);
                    addToProduct(nonEdibleProduct);
                } else if (product.length == 5) {
                    EdibleProduct edibleProduct = new EdibleProduct(product[0], Integer.parseInt(product[1]), Integer.parseInt(product[2]), Integer.parseInt(product[3]), Boolean.parseBoolean(product[4]));
                    addToProduct(edibleProduct);
                }
            }
        }
        catch(IOException e) {
            System.out.println("There is no file to read.");
            e.printStackTrace();
        }
    }

    public Person findPerson(String fullName) {
        for(Person person: persons) {
            if (person.getFullName().equals(fullName)) {
                return person;
            }
        }
        return null;
    }

    public Customer findCustomer(String fullName) {
        for(Person person: persons) {
            if(person instanceof Customer) {
                if(person.getFullName().equals(fullName)) {
                    return (Customer)person;
                }
            }
        }
        return null;
    }

    public void exit() {
        System.out.println("Have a nice day! Goodbye!");
    }
}