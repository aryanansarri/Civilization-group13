package Models.Info;

public class CityFood {
    private double foodAmount;

    public CityFood() {
        this.foodAmount = 0;
    }

    public void increaseFood(double amount) {
        this.foodAmount += amount;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(double foodAmount) {
        foodAmount = foodAmount;
    }
}