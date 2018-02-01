package com.codecool;

import java.io.*;

public abstract class ProductStorage implements Serializable {

    protected Product[] products;

    protected ProductStorage() {
        products = new Product[0];
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void addToProduct(Product product) {
        Product[] tempArray = new Product[products.length + 1];
        for(int i = 0; i < products.length; i++) {
            tempArray[i] = products[i];
        }
        tempArray[tempArray.length - 1] = product;
        products = tempArray;
    }

    public Product findProduct(String name) {
        for(Product product: products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

}