package Models.Info;

public class CivilizationScience {
    private double scienceValue;
    private double cheatedScienceValue;

    public CivilizationScience() {
        scienceValue = cheatedScienceValue = 0;
    }

    public double getScienceValue() {
        return scienceValue;
    }

    public void setScienceValue(double scienceValue) {
        this.scienceValue = scienceValue;
    }

    public double getCheatedScienceValue() {
        return cheatedScienceValue;
    }

    public void setCheatedScienceValue(double cheatedScienceValue) {
        this.cheatedScienceValue = cheatedScienceValue;
    }

    public void increaseScienceValue(double amount) {
        this.scienceValue += amount;
    }

    public void cheatScienceValue(double amount) {
        this.cheatedScienceValue = amount;
    }
}