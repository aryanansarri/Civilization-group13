package Models.Units;

import Models.Block.Tile;
import Models.Civilization.Civilization;

import java.util.ArrayList;

public class Unit {
    private int MovementPoint;
    private double HP;
    private int mana;
    private Tile tile;
    private Civilization civilization;
    private int combatStrength;
    public int COST;
    private boolean isSleep;
    protected boolean isOnFortify;
    private boolean isOnAlert;
    protected boolean isOnGarrison;
    private String type;
    public int lastDutyTurn;



    public Unit(String type, Tile tile, Civilization civilization) {
        this.type=type;
        this.tile=tile;
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

    public Civilization getCivilization() {
        return civilization;
    }

    public int getCOST() {
        return COST;
    }

    public int getMovementPoint() {
        return MovementPoint;
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
}