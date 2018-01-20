package com.codecool;

import java.util.*;
import java.io.*;

public class Supermarket {

    private String name;
    private Person[] persons;
    private Product[] products;

    public Supermarket(String name) {
        this.name = name;
        persons = new Person[0];
        products = new Product[0];
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

    public Product[] getProducts() {
        return products;
    }

    public void addToPerson(Person person) {
        Person[] tempArray = new Person[persons.length + 1];
        for(int i = 0; i < persons.length; i++) {
            tempArray[i] = persons[i];
        }
        tempArray[tempArray.length - 1] = person;
        persons = tempArray;
    }

    public void addToProduct(Product product) {
        Product[] tempArray = new Product[products.length + 1];
        for(int i = 0; i < products.length; i++) {
            tempArray[i] = products[i];
        }
        tempArray[tempArray.length - 1] = product;
        products = tempArray;
    }

    public Customer createCustomer(PersonType personType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your full name?");
        String customerFullName = sc.nextLine();
        System.out.println("What is your gender?");
        String customerGender = sc.nextLine();
        System.out.println("How old are you?");
        int customerAge = sc.nextInt();
        System.out.println("How much money have you got?");
        int customerMoney = sc.nextInt();
        Customer person = new Customer(customerFullName, customerGender, customerAge, customerMoney, personType);
        return person;
    }

    public Employee createEmployee(PersonType personType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your full name?");
        String employeeFullName = sc.nextLine();
        System.out.println("What is your gender?");
        String employeeGender = sc.nextLine();
        System.out.println("How old are you?");
        int employeeAge = sc.nextInt();
        System.out.println("What is your position?");
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

    public void uploadProduct(String csvFile) {
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
}