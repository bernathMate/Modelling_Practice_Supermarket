package com.codecool;

import java.util.*;
import java.io.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Supermarket sm;

    public static void main(String[] args) {
        sm = new Supermarket("Penny Market");
        handleStartLoad();
        sm.init();
        System.out.println("Available commands: " +
            "\n:listPersons" +
            "\n:listProducts" +
            "\n:createPerson" +
            "\n:findPerson" +
            "\n:findProduct" +
            "\n:putProductIntoCart" +
            "\n:takeOutProductFromCart" +
            "\n:shoppingCartContent" +
            "\n:pay" +
            "\n:loadLatestVersion" +
            "\n:saveAndExit" +
            "\n:exit\n");
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
            
            } else if (":putProductIntoCart".equals(line)) {
                putProductIntoCart();

            } else if (":takeOutProductFromCart".equals(line)) {
                takeOutProductFromCart();

            } else if (":shoppingCartContent".equals(line)) {
                shoppingCartContent();

            } else if (":pay".equals(line)) {
                pay();

            } else if (":saveAndExit".equals(line)) {
                handleSaveAndExit();
                break;
            } else {
                System.out.println("Invalid command, please give a correct command!");
            }
        }
        sm.exit();
    }

    private static void handleStartLoad() {
        System.out.println("Do you want to load the latest version? (y/n)");
        String yesOrNo = sc.nextLine();
        if(yesOrNo.equals("y")) {
            handleLoadLatestSimulation();
        }
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

    private static void addProductToShoppingCart(ProductStorage from, ProductStorage to) {
        System.out.println("Enter the product's name:");
        String productName = sc.nextLine();

        Product product = from.findProduct(productName);
        if (product == null) {
            System.out.println("No such product in there!");
            return;
        }

        Product chosenProduct = to.findProduct(productName);
        if (chosenProduct == null) {
            chosenProduct = Product.copyProduct(product);
            chosenProduct.setAmount(0);
            to.addToProduct(chosenProduct);
        }
        
        System.out.println("Okey, now enter how many:");
        int productAmount = sc.nextInt();
        if (productAmount <= product.getAmount()) {
            product.decreaseAmount(productAmount);
            chosenProduct.increaseAmount(productAmount);
            System.out.println("Okey, it's done!");
        } else {
            System.out.println("There are only " + product.getAmount() + " " + product.getName() + " in there");
        }
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

    public static void putProductIntoCart() {
        System.out.println("Enter the customer's name:");
        String customerName = sc.nextLine();

        Person customer = sm.findPerson(customerName);
        if (customer != null) {
            addProductToShoppingCart(sm, customer);
        } else {
            System.out.println("No such customer in the supermarket!");
        }
    }

    public static void takeOutProductFromCart() {
        System.out.println("Enter the customer's name:");
        String customerName = sc.nextLine();

        Person customer = sm.findPerson(customerName);
        if (customer != null) {
            addProductToShoppingCart(customer, sm);
        } else {
            System.out.println("No such customer in the supermarket!");
        }
    }

    public static Customer pay() {
        System.out.println("Enter the customer's name:");
        String customerName = sc.nextLine();

        Customer customer = sm.findCustomer(customerName);
        if ((customer != null) && (customer.costOfProducts() <= customer.getMoney())) {
            customer.decreaseMoney(customer.costOfProducts());
            customer.setProducts(new Product[0]);
            System.out.println("Your payment was succesfull! Thanks!");
            return customer;
        } else {
            System.out.println("No such customer in the supermarket!");
        }
        return null;
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