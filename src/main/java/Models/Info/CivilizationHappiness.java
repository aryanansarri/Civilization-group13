package Models.Info;

import Controller.GameController.GameDatabase;
import Models.Civilization.City;

import java.util.ArrayList;

public class CivilizationHappiness {
    private double happinessValue;
    private double cheatedHappinessValue;

    public CivilizationHappiness() {
        happinessValue = cheatedHappinessValue = 0;
    }

    public double getHappinessValue() {
        return happinessValue;
    }

    public void setHappinessValue(double happinessValue) {
        this.happinessValue = happinessValue;
    }

    public double getCheatedHappinessValue() {
        return cheatedHappinessValue;
    }

    public void setCheatedHappinessValue(double cheatedHappinessValue) {
        this.cheatedHappinessValue = cheatedHappinessValue;
    }

    public void increaseHappinessValue(double amount) {
        this.happinessValue += amount;
    }

    public void decreaseHappinessValue(double amount) {
        this.happinessValue -= amount;
    }

    public void cheatHappinessValue(double amount) {
        this.cheatedHappinessValue = amount;
    }

    public void nextTurn() {
        ArrayList<City> ourCities = GameDatabase.getGameDatabase().getCivilizationByHappiness(this).getCities();
        int numberOfCity = ourCities.size();
        decreaseHappinessValue(numberOfCity * 2);
        for (City city : ourCities) {
            int numberOfCitizen = city.getCitizens().size();
            decreaseHappinessValue(numberOfCitizen / 3);
        }
    }
}
