package Models.Info;

import View.GameView;

public class CivilizationGold {
    private double GoldAmount;
    private double addedGoldAmount;
    private double cheatedGoldAmount;


    public CivilizationGold() {
        GoldAmount = addedGoldAmount = cheatedGoldAmount = 0;
    }

    public double getGoldAmount() {
        return GoldAmount;
    }

    public void setGoldAmount(double goldAmount) {
        GoldAmount = goldAmount;
    }

    public double getAddedGoldAmount() {
        return addedGoldAmount;
    }

    public void setAddedGoldAmount(double addedGoldAmount) {
        this.addedGoldAmount = addedGoldAmount;
    }

    public double getCheatedGoldAmount() {
        return cheatedGoldAmount;
    }

    public void setCheatedGoldAmount(double cheatedGoldAmount) {
        this.cheatedGoldAmount = cheatedGoldAmount;
    }

    public void increaseGoldAmount(double amount) {
        this.GoldAmount += amount;
    }

    public void increaseAddedGoldAmount(double amount) {
        this.addedGoldAmount += amount;
    }

    public void cheatGold(double amount) {
        this.cheatedGoldAmount = amount;
    }
}
