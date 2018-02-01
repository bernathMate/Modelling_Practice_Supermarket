package com.codecool;

import java.util.*;

public class Customer extends Person {

    private int money;
    private Product[] shoppingCart;
    private static final int SHOPPING_CART_CAPACITY = 15;

    public Customer(String fullName, String gender, int age, int money, PersonType personType) {
        super(fullName, gender, age, personType);
        this.money = money;
        shoppingCart = new Product[0];
    }

    public int getMoney() {
        return money;
    }

    public Product[] getShoppingCart() {
        return shoppingCart;
    }

    public void printShoppingCartContent() {
        System.out.println("Shopping cart content: ");
        for (int i = 0; i < shoppingCart.length; i++) {
            System.out.println("\t" + shoppingCart[i]);
        }
    }
    
    public void addToShoppingCart(Product product) {
        if (shoppingCart.length <= SHOPPING_CART_CAPACITY) {
            Product[] tempArray = new Product[shoppingCart.length + 1];
            for(int i = 0; i < shoppingCart.length; i++) {
                tempArray[i] = shoppingCart[i];
            }
            tempArray[tempArray.length - 1] = product;
            shoppingCart = tempArray;
        } else {
            System.out.println("Your shopping cart's capacity is full!");
        }
    }

    public void deleteFromShoppingCart(Product product) {
        if (shoppingCart.length > 0) {
            Product[] tempArray = new Product[shoppingCart.length - 1];
            int tempVar = 0;
            for(int i = 0; i < shoppingCart.length; i++) {
                if(shoppingCart[i].getName().equals(product.getName())) {
                    tempVar = 1;
                } else {
                    tempArray[i - tempVar] = shoppingCart[i]; 
                }
            }
        } else {
            System.out.println("You can't remove a product because your shopping cart is empty!");
        }
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