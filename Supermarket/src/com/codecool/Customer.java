public class Customer extends Person {

    private int money;
    private Product[] shoppingCart;

    public Customer(String fullName, String gender, int age, int money) {
        super(fullName, gender, age);
        this.money = money;
        shoppingCart = new Product[30];
    }
}