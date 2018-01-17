public class Supermarket {
    private String name;
    private Person[] persons;
    private Product[] products;

    public Supermarket(String name) {
        this.name = name;
        persons = new Person[0];
        products = new Product[0];
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
}