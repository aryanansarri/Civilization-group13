package Models.Units;

import Models.Block.Tile;
import Models.Civilization.Civilization;
import Models.Cordination;
import Models.Select;

import java.util.ArrayList;

public class Unit implements Select, Combatble {
    private int MovementPoint;
    private int xp;
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
    private boolean workDone;
    private ArrayList<Cordination> path = new ArrayList<>();
    private String type;
    public int lastDutyTurn;

    private int remindMove;

    public Unit(String type, Tile tile, Civilization civilization) {
        this.type=type;
        this.tile=tile;
        this.civilization=civilization;
    }

    public Tile getTile() {
        return tile;
    }

    public Unit(int xp, int HP, int mana, Tile tile, int COST, int combatStrength) {
        this.xp = xp;
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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

    public void setHP(double HP) {
        this.HP = HP;
    }

    public void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }

    public boolean isOnFortify() {
        return isOnFortify;
    }

    public void setOnFortify(boolean onFortify) {
        isOnFortify = onFortify;
    }

    public boolean isOnAlert() {
        return isOnAlert;
    }

    public void setOnAlert(boolean onAlert) {
        isOnAlert = onAlert;
    }

    public boolean isOnGarrison() {
        return isOnGarrison;
    }

    public void setOnGarrison(boolean onGarrison) {
        isOnGarrison = onGarrison;
    }

    public boolean isWorkDone() {
        return workDone;
    }

    public void setWorkDone(boolean workDone) {
        this.workDone = workDone;
    }

    public ArrayList<Cordination> getPath() {
        return path;
    }

    public void setPath(ArrayList<Cordination> path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRemindMove() {
        return remindMove;
    }

    public void setRemindMove(int remindMove) {
        this.remindMove = remindMove;
    }

    @Override
    public void attack(Combatble combatble) {
//        to do
    }

    @Override
    public void defence(Combatble combatble) {
//        to do
    }

    @Override
    public String toString() {
        String str = "";
        str += type + " at " + tile.getX() + ", " + tile.getY() + " ";
        str += "sleep: " + isSleep + " " + "on alert: " + isOnAlert + " ";
        str += "work done: " + workDone + " " + "hp: " + getHP() + " " ;
        str += "remind mp" + remindMove + " " + "path size: " + path.size();
        return str;
    }
}