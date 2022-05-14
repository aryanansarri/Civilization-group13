package Models.Units;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.Civilization;
import Models.Cordination;

import java.util.ArrayList;

public class Unit {
    private int MovementPoint;
    private double HP;
    private int mana;
    private Tile tile;
    private static Civilization civilization;
    private int combatStrength;
    public int COST;
    private boolean isSleep;
    protected boolean isOnFortify;
    private boolean isOnAlert;
    protected boolean isOnGarrison;
    private String type;
    public int lastDutyTurn;
    private boolean dutyCompleted ;
    private UnitType unitType;

    private final ArrayList<Cordination> unitPath = new ArrayList<>();


    public Unit(Civilization civilization, UnitType unitType, Tile tile) {
        this.tile=tile;
        this.unitType = unitType;
        this.civilization=civilization;
    }

    public Tile getTile() {
        return tile;
    }

    public Unit(int HP, int mana, Tile tile, int COST, int combatStrength) {
        this.HP = HP;
        this.mana =mana;
        this.tile = tile;
        this.COST = COST;
        this.combatStrength = combatStrength;
    }
    public void garrison() {
        this.isOnGarrison = true;
    }
    public void sleep() {
        this.isSleep = true;
    }
    public void fortify() {
        this.isOnFortify = true;
    }
    public void alert() {
        this.isOnAlert = true;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public void wakeUp(){
        this.isSleep = false;
    }
    public int getMana() {
        return mana;
    }
    public void setHP(int hp){ this.HP = hp;}
    public void setMovementPoint(int movementPoint ){this.MovementPoint = movementPoint;}
    public double getHp() {return this.HP;}

    public void setLastDutyTurn(int lastDutyTurn) {
        this.lastDutyTurn = lastDutyTurn;
    }

    public int getLastDutyTurn() {
        return lastDutyTurn;
    }

    public double getHP() {
        return HP;
    }

    public static Civilization getCivilization() {
        return civilization;
    }

    public int getCOST() {
        return COST;
    }

    public int getMovementPoint() {
        return MovementPoint;
    }

    public void setDutyCompleted(boolean dutyCompleted) {
        this.dutyCompleted = dutyCompleted;
    }

    public void setCOST(int COST) {
        this.COST = COST;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public void istheTileVisible(int x, int y) {
        //will be boolean l8er
    }
    public void move(Tile tile){
        this.tile = tile;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public void deleteUnit() {
        getCivilization().removeUnit(this);
        for (Tile[] tile : GameDatabase.getOriginalMap().getTiles()) {
            for (Tile TILE : tile) {
                if (TILE.getCivilianUnit() == this) TILE.setCivilianUnit(null);
                if (TILE.getMilitaryUnit() == this) TILE.setMilitaryUnit(null);
            }
        }
    }
    public void capturedBy(Civilization civilization) {
        if (!(this instanceof MilitaryUnit)) this.setCivilization(civilization);
        else deleteUnit();
    }
}