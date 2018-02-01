package com.codecool;

import java.util.*;

public class Customer extends Person {

    private int money;
    private static final int SHOPPING_CART_CAPACITY = 15;

    public Customer(String fullName, String gender, int age, int money, PersonType personType) {
        super(fullName, gender, age, personType);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void decreaseMoney(int money) {
        this.money -= money;
    }

    public void printShoppingCartContent() {
        if (products.length > 0) {
            System.out.println("Shopping cart content: ");
            for (int i = 0; i < products.length; i++) {
                System.out.println("\t" + products[i]);
            }
        } else {
            System.out.println("Shopping cart is empty!");
        }
    }
    
    @Override
    public void addToProduct(Product product) {
        if (products.length <= SHOPPING_CART_CAPACITY) {
            super.addToProduct(product);
        } else {
            System.out.println("Your shopping cart's capacity is full!");
        }
    }

    public void deleteFromShoppingCart(Product product) {
        if (products.length > 0) {
            Product[] tempArray = new Product[products.length - 1];
            int tempVar = 0;
            for(int i = 0; i < products.length; i++) {
                if(products[i].getName().equals(product.getName())) {
                    tempVar = 1;
                } else {
                    tempArray[i - tempVar] = products[i]; 
                }
            products = tempArray;
            }
        } else {
            System.out.println("You can't remove a product because your shopping cart is empty!");
        }
    }

    public int costOfProducts() {
        int costOfProducts = 0;
        if (products.length > 0) {
            for (int i = 0; i < products.length; i++) {
                int currentProductCost = products[i].getPrice() * products[i].getAmount();
                costOfProducts += currentProductCost;
            }
            return costOfProducts;
        } else {
            System.out.println("Your shopping cart is empty!");
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Name= " + getFullName()
                + ", Gender= " + getGender()
                + ", Age= " + getAge()
                + ", Money= " + getMoney()
                + ", Person's type= " + getPersonType();
    }
}