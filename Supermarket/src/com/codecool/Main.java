package com.codecool;

import java.util.*;
import java.io.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Supermarket sm;

    public static void main(String[] args) {
        sm = new Supermarket("Penny Market");
        sm.init();
        System.out.println("Available commands: :listPersons, :listProducts, :createPerson, :findPerson, :findProduct, :shopping, :shoppingCartContent, :loadLatestVersion, :saveAndExit, :exit");
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
            
            } else if (":loadLatestVersion".equals(line)) {
                handleLoadLatestSimulation();

            } else if (":shopping".equals(line)) {
                shopping();

            } else if (":shoppingCartContent".equals(line)) {
                shoppingCartContent();

            } else if (":saveAndExit".equals(line)) {
                handleSaveAndExit();
                break;
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

    private static Customer addProductToShoppingCart(Customer customer) {
        System.out.println("Enter the product's name:");
        String productName = sc.nextLine();

        Product product = sm.findProduct(productName);
        Product chosenProduct = Product.copyProduct(product);
        
        if (product != null) {
            System.out.println("Okey, now enter how many would you like to buy:");
            int productAmount = sc.nextInt();
            if (productAmount <= product.getAmount()) {
                product.decreaseAmount(productAmount);
                chosenProduct.setAmount(productAmount);
                customer.addToShoppingCart(chosenProduct);
                System.out.println("Okey, it's done!");
                return customer;
            } else {
                System.out.println("There are only " + product.getAmount() + " " + product.getName() + " in the supermarket");
            }
        } else {
            System.out.println("No such product in the supermarket!");
        }
        return null;
    }

    public static void shoppingCartContent() {
        System.out.println("Enter the person's full name:");
        String personFullName = sc.nextLine();

        Customer customer = sm.findCustomer(personFullName);
        if (customer != null) {
            customer.printShoppingCartContent();
        } else {
            System.out.println("No such customer in the supermarket!");
        }
    }

    public static void shopping() {
        System.out.println("Enter the customer's name:");
        String customerName = sc.nextLine();

        Person customer = sm.findPerson(customerName);
        if (customer != null) {
            customer = addProductToShoppingCart((Customer)customer);
        } else {
            System.out.println("No such customer in the supermarket!");
        }
    }

    private static void handleSaveAndExit() {
        try {
            FileOutputStream fileOut = new FileOutputStream("supermarket.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sm);
            out.close();
            fileOut.close();
            System.out.printf("Supermarket is saved!\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void handleLoadLatestSimulation() {
        sm = null;
        try {
            FileInputStream fileIn = new FileInputStream("supermarket.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sm = (Supermarket) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Supermarket class not found");
            c.printStackTrace();
            return;
        }
    }
    
}