package com.codecool;

public class NonEdibleProduct extends Product {

    private String material;

    public NonEdibleProduct(String name, int price, int amount, String material) {
        super(name, price, amount);
        this.material = material;
    }
    
    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Name= " + getName()
                + ", Price= " + getPrice()
                + ", Amount= " + getAmount()
                + ", Material= " + getMaterial();
    }
}