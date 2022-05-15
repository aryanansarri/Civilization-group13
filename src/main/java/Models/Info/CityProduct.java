package Models.Info;

public class CityProduct {
    private double productAmount;

    public CityProduct() {
        this.productAmount = 0;
    }

    public void increaseProduct(double amount) {
        this.productAmount += amount;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        productAmount = productAmount;
    }
}