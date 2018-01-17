public class NonEdibleProduct extends Product {

    private String material;

    public NonEdibleProduct(String name, int price, String material) {
        super(name, price);
        this.material = material;
    }
    
}