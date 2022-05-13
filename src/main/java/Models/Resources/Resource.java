package Models.Resources;

import Models.Improvment.Improvement;
import Models.Technology.Technology;

import java.util.ArrayList;
import java.util.Arrays;

import static Models.Improvment.Improvement.*;
import static Models.Technology.Technology.*;
import static Models.Resources.ResourcesType.*;

public enum Resource {
    //BonusResources:
    Banana(BonusResource,"Banana", 1, 0, 0, Farm, null),
    Cow(BonusResource,"Cow", 1, 0, 0, Pasture, null),
    Gazelle(BonusResource,"Gazelle", 1, 0, 0, Camp, null),
    Sheep(BonusResource,"Sheep", 1, 0, 0, Pasture, null),
    Wheat(BonusResource,"Wheat", 1, 0, 0, Farm, null),

    //LuxuryResources:
    Cotton(LuxuryResource,"Cotton", 0, 0, 2, Farm, null),
    Color(LuxuryResource,"Color", 0, 0, 2, Farm, null),
    Fur(LuxuryResource,"Fur", 0, 0, 2, Camp, null),
    Jewel(LuxuryResource,"Jewel", 0, 0, 3, Mine, null),
    Gold(LuxuryResource,"Gold", 0, 0, 2, Mine, null),
    Incense(LuxuryResource,"Incense", 0, 0, 2, Farm, null),
    Ivory(LuxuryResource,"Ivory", 0, 0, 2, Camp, null),
    Marble(LuxuryResource,"Marble", 0, 0, 2, Quarry, null),
    Silk(LuxuryResource,"Silk", 0, 0, 2, Farm, null),
    Silver(LuxuryResource,"Silver", 0, 0, 2, Mine, null),
    Sugar(LuxuryResource,"Sugar", 0, 0, 2, Farm, null),

    //StrategicResources:
    Coal(StrategicResource,"Coal", 0, 1, 0, Mine, SCIENTIFIC_THEORY),
    Horse(StrategicResource,"Horse", 0, 1, 0, Pasture, Animal_Husbandry),
    Iron(StrategicResource,"Iron", 0, 1, 0, Mine, Iron_Working);

    private int food;
    private int production;
    private int gold;
    private Improvement requiredImprovement;
    private Technology requiredTechnology;
    private String name;
    private ResourcesType type;

    Resource(ResourcesType type,String name, int food, int production, int gold, Improvement requiredImprovement, Technology requiredTechnology) {
        this.type = type;
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.name = name;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
    }

    public ResourcesType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    public static ArrayList<Resource> getAllResources(){
        return new ArrayList<>(Arrays.asList(Resource.class.getEnumConstants()));
    }

    public void setFields(ResourcesType type, int food, int production, int gold, Improvement requiredImprovement, Technology requiredTechnology) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
        this.type = type;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public int getFood() {
        return food;
    }

    public Improvement getRequiredImprovement() {
        return requiredImprovement;
    }

    public Technology getRequiredTechnology() {
        return requiredTechnology;
    }



}