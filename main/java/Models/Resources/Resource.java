package Models.Resources;


import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Building.Improvement;
import Models.Building.Technology;
import Models.Resources.ResourcesType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Models.Block.TerrainType.*;
import static Models.Building.Improvement.*;
import static Models.Building.Technology.*;
import static Models.Resources.ResourcesType.*;


public enum Resource {
    //BonusResources:
    Banana(BonusResource,"Banana", 1, 0, 0, Pasture, null,new ArrayList<TerrainFeature>(List.of(TerrainFeature.Jungle)),new ArrayList<TerrainType>(List.of())),
    Deer(BonusResource,"Deer", 1, 0, 0, Pasture, null,new ArrayList<TerrainFeature>(List.of(TerrainFeature.Forest)),new ArrayList<TerrainType>(Arrays.asList(Tundra,Hills))),
    Cow(BonusResource,"Cow", 1, 0, 0, Camp, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(List.of(GrassLand))),
    Sheep(BonusResource,"Sheep", 1, 0, 0, Pasture, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Hills))),
    Wheat(BonusResource,"Wheat", 1, 0, 0, Farm, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(List.of(GrassLand))),

    //LuxuryResources:
    Cotton(LuxuryResource,"Cotton", 0, 0, 2, Farm, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert))),
    Color(LuxuryResource,"Color", 0, 0, 2, Farm, null,new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.Jungle,TerrainFeature.Forest)),new ArrayList<TerrainType>(List.of())),
    Fur(LuxuryResource,"Fur", 0, 0, 2, Camp, null,new ArrayList<TerrainFeature>(List.of(TerrainFeature.Forest)),new ArrayList<TerrainType>(List.of(Tundra))),
    Jewel(LuxuryResource,"Jewel", 0, 0, 3, Mine, null,new ArrayList<TerrainFeature>(List.of(TerrainFeature.Jungle)),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Hills,Tundra))),
    Gold(LuxuryResource,"Gold", 0, 0, 2, Mine, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Hills))),
    Incense(LuxuryResource,"Incense", 0, 0, 2, Farm, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(Plains,Desert))),
    Ivory(LuxuryResource,"Ivory", 0, 0, 2, Camp, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(List.of(Plains))),
    Marble(LuxuryResource,"Marble", 0, 0, 2, Quarry, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Hills,Tundra))),
    Silk(LuxuryResource,"Silk", 0, 0, 2, Farm, null,new ArrayList<TerrainFeature>(List.of(TerrainFeature.Forest)),new ArrayList<TerrainType>(List.of())),
    Silver(LuxuryResource,"Silver", 0, 0, 2, Mine, null,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(Desert,Hills,Tundra))),
    Sugar(LuxuryResource,"Sugar", 0, 0, 2, Farm, null,new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.FloodPlains,TerrainFeature.Marsh)),new ArrayList<TerrainType>(List.of())),

    //StrategicResources:
    Coal(StrategicResource,"Coal", 0, 1, 0, Mine, SCIENTIFIC_THEORY,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Hills))),
    Horse(StrategicResource,"Horse", 0, 1, 0, Pasture, Animal_Husbandry,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Tundra))),
    Iron(StrategicResource,"Iron", 0, 1, 0, Mine, Iron_Working,new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Tundra,Desert,Snow,Hills)));

    private int food;
    private int production;
    private int gold;
    private Improvement requiredImprovement;
    private Technology requiredTechnology;
    private final ArrayList<TerrainFeature> possibleTerrainFeature;
    private final ArrayList<TerrainType> possibleTerrainType;
    private String name;
    private ResourcesType type;

    Resource(ResourcesType type,String name, int food, int production, int gold, Improvement requiredImprovement, Technology requiredTechnology,ArrayList<TerrainFeature> possibleTerrainFeature,ArrayList<TerrainType> possibleTerrainType) {
        this.type = type;
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.name = name;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
        this.possibleTerrainFeature = possibleTerrainFeature;
        this.possibleTerrainType = possibleTerrainType;

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
    public static ArrayList<Resource> getLuxuryResources() {
        ArrayList<Resource> resource = new ArrayList<>();
        for (Resource resources : getAllResources()) {
            if (resources.getType() == LuxuryResource) resource.add(resources);} return resource;
    }
    public static ArrayList<Resource> getBonusResources() {
        ArrayList<Resource> resource = new ArrayList<>();
        for (Resource resources : getAllResources()) {
            if (resources.getType() == BonusResource) resource.add(resources);} return resource;
    }
    public static ArrayList<Resource> getStrategicResources() {
        ArrayList<Resource> resource = new ArrayList<>();
        for (Resource resources : getAllResources()) {
            if (resources.getType() == StrategicResource) resource.add(resources);} return resource;
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

    public ArrayList<TerrainFeature> getPossibleTerrainFeature() {
        return possibleTerrainFeature;
    }

    public ArrayList<TerrainType> getPossibleTerrainType() {
        return possibleTerrainType;
    }

    public Improvement getRequiredImprovement() {
        return requiredImprovement;
    }

    public Technology getRequiredTechnology() {
        return requiredTechnology;
    }



}
