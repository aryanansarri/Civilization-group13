package Models.Info;

public class CityGold {
    private double GoldAmount;

    public CityGold() {
        this.GoldAmount = 0;
    }

    public void increaseGold(double amount) {
        this.GoldAmount += amount;
    }

    public double getGoldAmount() {
        return GoldAmount;
    }

    public void setGoldAmount(double goldAmount) {
        GoldAmount = goldAmount;
    }
}
