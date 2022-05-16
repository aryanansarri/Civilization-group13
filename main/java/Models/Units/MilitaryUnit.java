package Models.Units;


import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.Civilization;

public class MilitaryUnit extends Unit{
    private int XP;
    private int lastActionTurn;


    public MilitaryUnit(Civilization civilization, UnitType unitType, Tile tile){
        super(unitType,tile,civilization);
    }

    public int getLastActionTurn() {
        return lastActionTurn;
    }

    public boolean isOnGarrison() {
        return isOnGarrison;
    }

    public double getCombatStrength(int thisTurn) {
        double strength =this.getCombatStrength();
        if (this.isOnFortify){
            for (int i = lastActionTurn;i<thisTurn;i++){
                strength = 1.25 * getCombatStrength();
            }
        }
        else if (this.isOnGarrison){
            for (int i = lastActionTurn;i<thisTurn;i++){
                strength = 1.25 * getCombatStrength();
            }
        }
        strength += strength * getTile().getCombatModifier();
        return strength;
    }

    public void move(Tile tile) {
        super.move(tile);
//this is different than rest
    }
}