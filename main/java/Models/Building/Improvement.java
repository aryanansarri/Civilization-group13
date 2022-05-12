package Models.Building;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Resources.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Models.Block.TerrainType.*;
import static Models.Building.Technology.*;


public enum Improvement {



    Camp("Camp",0,0,0,6,Trapping ,new ArrayList<Resource>(Arrays.asList(Resource.Ivory,Resource.Fur,Resource.Deer)),new ArrayList<TerrainFeature>(List.of(TerrainFeature.Forest)),new ArrayList<TerrainType>(Arrays.asList(Tundra,Plains,Hills))),
    Farm("Farm",1,0,0,6,Agriculture,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Hills))),
    Lumber_Mill("Lumber_Mill",0,1,0,6,Engineering,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert))),
    Mine("Mine",0,1,0,6,Mining,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.Forest,TerrainFeature.Jungle)),new ArrayList<TerrainType>(Arrays.asList(Plains,Hills,Desert,Tundra,Snow))),
    Pasture("Pasture",0,0,0,7,Animal_Husbandry,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Tundra,Hills))),
    Plantation("Plantation",0,0,0,5,Calendar,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.Forest,TerrainFeature.Marsh,TerrainFeature.FloodPlains,TerrainFeature.Jungle)),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert))),
    Quarry("Quarry",0,0,0,7,Masonry,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Tundra,Hills))),
    Trading_Post("Trading_Post",0,0,1,8,Trapping,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Tundra))),
    Manufactory("Manufactory",0,2,0,0,Engineering,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GrassLand,Plains,Desert,Snow,Tundra)));

    private final int food;
    private final int gold;
    private final int production;
    private final int turn;
    private final Technology neededTech;
    private final ArrayList<Resource> resourceImprovement;
    private final ArrayList<TerrainFeature> possibleTerrainFeatures;
    private final ArrayList<TerrainType> possibleTerrainTypes;

    private String type;


    Improvement(String Type, int food, int production, int gold , int turn , Technology neededTech, ArrayList<Resource> resourceImprovement, ArrayList<TerrainFeature> possibleTerrainFeatures, ArrayList<TerrainType> possibleTerrainTypes){
        this.type=type;
        this.food=food;
        this.gold=gold;
        this.turn = turn;
        this.production=production;
        this.neededTech=neededTech;
        this.resourceImprovement = resourceImprovement;
        this.possibleTerrainFeatures = possibleTerrainFeatures;
        this.possibleTerrainTypes = possibleTerrainTypes;

    }
    public static ArrayList<Improvement> getAllImprovements(){
        return new ArrayList<>(Arrays.asList(Improvement.class.getEnumConstants()));
    }

    public String getType() {
        return type;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public int getFood() {
        return food;
    }

    public ArrayList<Resource> getResourceImprovement() {
        return resourceImprovement;
    }

    public ArrayList<TerrainFeature> getPossibleTerrainFeatures() {
        return possibleTerrainFeatures;
    }

    public ArrayList<TerrainType> getPossibleTerrainTypes() {
        return possibleTerrainTypes;
    }

    public int getTurn() {
        return turn;
    }

    public Technology getNeededTech() {
        return neededTech;
    }

    public boolean isImprovementPossible(Tile tile) {
        if (!(neededTech == null || GameDatabase.getCurrentCivilization().getCivilizationTechnology().getPassedTechnology().contains(neededTech))) return false;
        if (possibleTerrainTypes.contains(tile.getType())||possibleTerrainFeatures.contains(tile.getType())) {return true;}
        for (TerrainFeature terrainfeature : tile.getTerrainFeatures()) {
            if (possibleTerrainFeatures.contains(terrainfeature)) {return true;}}
        return false;}
}

