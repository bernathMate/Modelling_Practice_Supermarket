public class EdibleProduct extends Product {

    private int calorie;
    private boolean isAlcohol;

    public EdibleProduct(String name, int price, int calorie, boolean isAlcohol) {
        super(name, price);
        this.calorie = calorie;
        this.isAlcohol = isAlcohol;
    }

    public int getCalorie() {
        return calorie;
    }

    public boolean getIsAlcohol() {
        return isAlcohol;
    }
}