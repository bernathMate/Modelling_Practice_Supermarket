package com.codecool;

import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Supermarket sm;

    public static void main(String[] args) {
        sm = new Supermarket("Penny Market");
        sm.init();
        System.out.println("Available commands: :listPersons, :listProducts, :createPerson, :findPerson, :findProduct, :exit");
        sm.uploadProducts("EdibleProducts.csv");
        sm.uploadProducts("NonEdibleProducts.csv");
        while (true) {
            String line = sc.nextLine();
            if (":exit".equals(line)) {
                break;

            } else if (":listPersons".equals(line)) {
                handleListPersons();

            } else if (":listProducts".equals(line)) {
                handleListProducts();

            } else if (":createPerson".equals(line)) {
                handleCreatePerson();

            } else if (":findPerson".equals(line)) {
                handleFindPerson();

            } else if (":findProduct".equals(line)) {
                handleFindProduct();
            }
        }
        sm.exit();
    }

    private static void handleListPersons() {
        Person[] persons = sm.getPersons();

        System.out.println("Let's see who here in the supermarket!");
        if (persons.length != 0) {
            System.out.println("\tEMPLOYEES:");
            for (Person person: persons) {
                if (person instanceof Employee) {
                    System.out.println("\t\t" + person);
                }
            }
            System.out.println("\tCUSTOMERS:");
            for (Person person: persons) {
                if (person instanceof Customer) {
                    System.out.println("\t\t" + person);
                }
            }
        } else {
            System.out.println("No one is here, yet.");
        }
    }

    private static void handleListProducts() {
        Product[] products = sm.getProducts();

        System.out.println("Let's see the products in the supermarket!");
        if (products.length != 0) {
            System.out.println("\tEDIBLE PRODUCTS:");
            for (Product product: products) {
                if (product instanceof EdibleProduct) {
                    System.out.println("\t\t" + product);
                }
            }
            System.out.println("\tNON-EDIBLE PRODUCTS:");
            for (Product product: products) {
                if (product instanceof NonEdibleProduct) {
                    System.out.println("\t\t" + product);
                }
            }
        } else {
            System.out.println("No one product is here, yet.");
        }

    }

    private static void handleCreatePerson() {
        System.out.println("What is the type of the person? (CUSTOMER or EMPLOYEE)");
        String typeAsString = sc.nextLine();
        PersonType personType;
        try {
            personType = PersonType.valueOf(typeAsString);
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong type entered! (valid types: CUSTOMER or EMPLOYEE)");
            return;
        }

        sm.createPerson(personType);

        System.out.println("Okey, it's done!");
    }

    private static void handleFindPerson() {
        System.out.println("Enter the person's full name:");
        String personFullName = sc.nextLine();

        Person person = sm.findPerson(personFullName);
        if (person != null) {
            System.out.println("\tPerson's details:");
            System.out.println("\t\t" + person);
        } else {
            System.out.println("No such person in the supermarket!");
        }

    }

    private static void handleFindProduct() {
        System.out.println("Enter the product's name:");
        String productName = sc.nextLine();

        Product product = sm.findProduct(productName);
        if (product != null) {
            System.out.println("\tProduct's details:");
            System.out.println("\t\t" + product);
        } else {
            System.out.println("No such product in the supermarket!");
        }
    }
}