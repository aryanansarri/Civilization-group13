package ModelsTest;

import Models.Info.*;
import Models.Technology.Technology;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoTest {
    private CityFood cityFood = new CityFood();
    private CityGold cityGold = new CityGold();
    private CityProduct cityProduct = new CityProduct();
    private CityScience cityScience = new CityScience();
    private CivilizationGold civilizationGold = new CivilizationGold();
    private CivilizationHappiness civilizationHappiness = new CivilizationHappiness();
    private CivilizationScience civilizationScience = new CivilizationScience();
    private CivilizationTechnology civilizationTechnology = new CivilizationTechnology();

    @Test
    public void test1() {
        cityFood.increaseFood(200);
        cityFood.setFoodAmount(10000);
        System.out.println(cityFood.getFoodAmount());
    }

    @Test
    public void test2() {
        cityGold.setGoldAmount(10000);
        cityGold.increaseGold(20);
        System.out.println(cityGold.getGoldAmount());
    }

    @Test
    public void test3() {
        cityProduct.setProductAmount(1000);
        cityProduct.increaseProduct(2);
        System.out.println(cityProduct.getProductAmount());
    }

    @Test
    public void test4() {
        cityScience.setScienceAmount(1000);
        cityScience.increaseScience(22);
        System.out.println(cityScience.getScienceAmount());
    }

    @Test
    public void test5() {
        civilizationGold.setGoldAmount(10000);
        civilizationGold.setGoldAmount(10000);
        civilizationGold.increaseAddedGoldAmount(4555);
        civilizationGold.cheatGold(15555);
        civilizationGold.setCheatedGoldAmount(25555);
        civilizationGold.cheatGold(5);
        civilizationGold.increaseGoldAmount(5155);
        System.out.println(civilizationGold.getGoldAmount() + civilizationGold.getAddedGoldAmount()
        +civilizationGold.getCheatedGoldAmount());
    }

    @Test
    public void test6() {
        try {

            civilizationHappiness.increaseHappinessValue(554);
            civilizationHappiness.setHappinessValue(1155);
            civilizationHappiness.decreaseHappinessValue(1);
            civilizationHappiness.cheatHappinessValue(5454);
            civilizationHappiness.setCheatedHappinessValue(5545);
            System.out.println(civilizationHappiness.getCheatedHappinessValue()
                    + civilizationHappiness.getHappinessValue() +
                    civilizationHappiness.getCheatedHappinessValue());
            civilizationHappiness.nextTurn();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test7() {
        civilizationScience.setScienceValue(55445);
        civilizationScience.increaseScienceValue(5454);
        civilizationScience.setCheatedScienceValue(54545);
        civilizationScience.cheatScienceValue(454545);
        System.out.println(
                civilizationScience.getScienceValue() +
                        civilizationScience.getCheatedScienceValue()
        );
    }

    @Test
    public void test8() {
        civilizationTechnology.setPassedTechnology(new ArrayList<>());
        civilizationTechnology.setTechnologyMap(new HashMap<>());
        civilizationTechnology.setCurrentTeachTechnology(Technology.Agriculture);
        civilizationTechnology.setNotPassedTechnology(new ArrayList<>());
        civilizationTechnology.setRemind(54);
        civilizationTechnology.getPassedTechnology();
        civilizationTechnology.getTechnologyMap();
        civilizationTechnology.getNotPassedTechnology();
        civilizationTechnology.getTechnologyMap();
        civilizationTechnology.currentTeachTechnologyProgress();
        civilizationTechnology.checkForReaching();
        civilizationTechnology.RequestToReachTechnology(Technology.Mathematics,5);
        try {
            System.out.println(civilizationTechnology.toString());
        }
        catch (Exception e) {
            
        }
    }
}
