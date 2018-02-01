package com.codecool;

public abstract class Product implements java.io.Serializable {

    public static Product copyProduct(Product p) {
        if (p instanceof EdibleProduct) {
            return new EdibleProduct((EdibleProduct)p);
        } else if (p instanceof NonEdibleProduct) {
            return new NonEdibleProduct((NonEdibleProduct)p);
        }
        return null;
    }

    private String name;
    private int price;
    private int amount;

    public Product(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Product(Product otherProduct) {
        this.name = otherProduct.name;
        this.price = otherProduct.price;
        this.amount = otherProduct.amount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decreaseAmount(int amount) {
        this.amount -= amount;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }
    
}