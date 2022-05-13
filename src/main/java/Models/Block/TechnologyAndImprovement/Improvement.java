package Models.Block.TechnologyAndImprovement;

import Models.Block.TerrainType;

import java.util.ArrayList;
import java.util.Arrays;

import static Models.Block.TechnologyAndImprovement.Technology.*;

public enum Improvement {



    Camp("Camp",0,0,0,Trapping ,new ArrayList<TerrainType>(Arrays.asList())),
    Farm("Farm",1,0,0,Agriculture,new ArrayList<TerrainType>(Arrays.asList())),
    Lumber_Mill("Lumber_Mill",0,1,0,Engineering,new ArrayList<TerrainType>(Arrays.asList())),
    Mine("Mine",0,1,0,Mining,new ArrayList<TerrainType>(Arrays.asList())),
    Pasture("Pasture",0,0,0,Animal_Husbandry,new ArrayList<TerrainType>(Arrays.asList())),
    Plantation("Plantation",0,0,0,Calendar,new ArrayList<TerrainType>(Arrays.asList())),
    Quarry("Quarry",0,0,0,Masonry,new ArrayList<TerrainType>(Arrays.asList())),
    Trading_Post("Trading_Post",0,0,1,Trapping,new ArrayList<TerrainType>(Arrays.asList())),
    Manufactory("Manufactory",0,2,0,Engineering,new ArrayList<TerrainType>(Arrays.asList()));

    private  int food;
    private  int gold;
    private  int production;
    private int neededturns;
    private Technology neededTech;
    private    ArrayList<TerrainType> possibleTerrains;
    private String type;


    Improvement(String Type,int food, int production, int gold, Technology neededTech, ArrayList<TerrainType> possibleTerrains){
        this.type=type;
        this.food=food;
        this.gold=gold;
        this.production=production;
        this.neededTech=neededTech;
        this.possibleTerrains=possibleTerrains;
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

    public ArrayList<TerrainType> getPossibleTerrains() {
        return possibleTerrains;
    }

    public Technology getNeededTech() {
        return neededTech;
    }

    public int getNeededturns() {
        return neededturns;
    }
}
