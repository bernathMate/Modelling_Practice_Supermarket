package com.codecool;

public class Customer extends Person {

    private int money;
    private Product[] shoppingCart;

    public Customer(String fullName, String gender, int age, int money, PersonType personType) {
        super(fullName, gender, age, personType);
        this.money = money;
        shoppingCart = new Product[30];
    }

    public int getMoney() {
        return money;
    }

    public Product[] getShoppingCart() {
        return shoppingCart;
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