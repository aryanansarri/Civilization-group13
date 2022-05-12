package Models.Block;

import Models.Civilization.Civilization;
import Models.Resources.Resource;
import Models.Units.CivilianUnit;
import Models.Units.MilitaryUnit;
import Models.Units.UnitType;

import java.util.ArrayList;

import static Models.Units.UnitType.removingNonSiege;

public enum TerrainType {


    Ocean(700, 0, 0, 0, 0, false),
    Desert(1, 0, 0, 0, 33 / 100, true),
    GrassLand(1, 0, 2, 0, 33 / 100, true),
    Meadows(2, 0, 0, 2, -25 / 100, true),
    Mountain(700, 0, 0, 0, 0, false),
    Plains(1, 0, 1, 1, 33 / 100, true),
    Antarctica(1, 0, 0, 0, 33 / 100, true),
    Tundra(1, 0, 1, 0, 33 / 100, true),
    Hills(2, 0,0 ,2 ,25 / 100 ,true ),
    Snow(1,0 ,0 ,0 ,-33 / 100 ,true );

    private String state;
    private int movementcost;
    private int gold;
    private int food;
    private int production;
    private double combatmodifier;
    private boolean ismovingpossible;
    private boolean isvisible=true;
    private TerrainFeature terrainfeature;
    private static MilitaryUnit militaryUnit;
    private static CivilianUnit civilianUnit;
    private static ArrayList<Resource> resources;

    TerrainType(int movementcost, int gold, int food, int production, float combatmodifier,
                boolean ismovingpossible) {
        this.movementcost = movementcost;
        this.gold = gold;
        this.food = food;
        this.production = production;
        this.combatmodifier = combatmodifier;
        this.ismovingpossible = ismovingpossible;
    }

    public void setFields(int movementcost, int gold, int food, int production, float combatmodifier,
                          boolean ismovingpossible) {
        this.movementcost = movementcost;
        this.gold = gold;
        this.food = food;
        this.production = production;
        this.combatmodifier = combatmodifier;
        this.ismovingpossible = ismovingpossible;
    }

    public static ArrayList<Resource> getResources() {
        return resources;
    }

    public double getCombatmodifier() {
        return combatmodifier;
    }


    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public static MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }
    public static ArrayList<UnitType> getSiegeUnit(){
        ArrayList<UnitType> SiegeUnit = new ArrayList<>();
        return removingNonSiege(SiegeUnit);
    }

    public static CivilianUnit getCivilianUnit() {
        return civilianUnit;
    }
    public void setCivilianUnit(CivilianUnit civilianUnit) {
        this.civilianUnit = civilianUnit;
    }
    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public int getMovementcost() {
        return movementcost;
    }

    public int getProduction() {
        return production;
    }

    public String getType() {
        return state;
    }

    public TerrainFeature getTerrainfeature() {
        return terrainfeature;
    }

    public boolean getIsmovingpossible() {
        return ismovingpossible;
    }

    public boolean getIsvisible() {
        return isvisible;
    }
}