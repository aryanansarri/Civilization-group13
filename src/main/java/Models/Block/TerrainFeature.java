package Models.Block;


import Models.Resources.Resource;

import java.util.ArrayList;

public enum TerrainFeature {

    FloodPlains(1, 0, 2, 0, 33/100, true),
    Ice(0, 0, 0, 0, 0, false),
    Forest(1, 0, 1, -1, -25 / 100, true),
    Jungle(2, 0, 1, 1, -25 / 100, true),
    Marsh(2, 0, -1, 0, 33/100, true),
    RIVER(0, 0, 1, 0, 0, false),

    Oasis(1, 1, 3, 0, 33 / 100, true);
    private int movementcost;
    int gold;
    int MP;
    private int food;
    private int production;
    private double combatmodifier;
    private boolean ismovingpossible;
    private boolean isvisible;

    TerrainFeature(int movementcost, int gold, int food, int production, float combatmodifier, boolean ismovingpossible) {
        this.movementcost = movementcost;
        this.gold = gold;
        this.food = food;
        this.production = production;
        this.combatmodifier = combatmodifier;
        this.ismovingpossible = ismovingpossible;
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

    public ArrayList<Resource> getPossibleResources() {
        return null;
    }
}