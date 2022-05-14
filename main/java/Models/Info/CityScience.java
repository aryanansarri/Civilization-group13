package Models.Info;

public class CityScience {
    private double scienceAmount;

    public CityScience() {
        this.scienceAmount = 0;
    }

    public void increaseScience(double amount) {
        this.scienceAmount += amount;
    }

    public double getScienceAmount() {
        return scienceAmount;
    }

    public void setScienceAmount(double scienceAmount) {
        scienceAmount = scienceAmount;
    }
}
