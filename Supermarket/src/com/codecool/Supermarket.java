import java.util.*;

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

    public Product createProduct(Pro)
}