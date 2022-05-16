package Models.Block;


import Models.Resources.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Models.Resources.Resource.*;
import static Models.Resources.Resource.Sugar;

public enum TerrainFeature {

    FloodPlains(1, 0, 2, 0, 33/100, true,new ArrayList<Resource>(Arrays.asList(Wheat,Sugar))),
    Ice(0, 0, 0, 0, 0, false,new ArrayList<Resource>()),
    Forest(1, 0, 1, -1, -25 / 100, true,new ArrayList<Resource>(Arrays.asList(Gazelle,Fur,Silk,Color))),
    Jungle(2, 0, 1, 1, -25 / 100, true,new ArrayList<Resource>(Arrays.asList(Banana,Color,Jewel))),
    Marsh(2, 0, -1, 0, 33/100, true,new ArrayList<Resource>(Arrays.asList(Sugar))),
    RIVER(-1,1,0,0,0,false,new ArrayList<Resource>()),
    Oasis(1, 1, 3, 0, 33 / 100, true,new ArrayList<Resource>());
    private int movementcost;
    int gold;
    int MP;
    private int food;
    private int production;
    private double combatmodifier;
    private boolean ismovingpossible;
    private boolean isvisible;
    private ArrayList<Resource> possibleResources;

    TerrainFeature(int movementcost, int gold, int food, int production, float combatmodifier, boolean ismovingpossible, ArrayList<Resource> possibleResources) {
        this.movementcost = movementcost;
        this.gold = gold;
        this.food = food;
        this.production = production;
        this.combatmodifier = combatmodifier;
        this.ismovingpossible = ismovingpossible;
        this.possibleResources = possibleResources;
    }

    public ArrayList<Resource> getPossibleResources() {
        return possibleResources;
    }
    public int getMP() {
        return MP;
    }

    public double getCombatModifier() {
        return combatmodifier;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getMovementcost() {
        return movementcost;
    }

    public int getProduction() {
        return production;
    }

    public boolean getIsvisible() {
        return isvisible;
    }

    public boolean getIsmovingpossible() {
        return ismovingpossible;
    }
}