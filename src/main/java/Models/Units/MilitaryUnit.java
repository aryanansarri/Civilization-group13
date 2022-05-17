package Models.Units;


import Models.Block.Tile;
import Models.Civilizations.City;
import Models.Civilizations.Civilization;

public class MilitaryUnit extends Unit{
    private int XP;
    private int lastActionTurn;
    private boolean isInAlert;
    private boolean isFortifyHeal;


    public MilitaryUnit(Civilization civilization, UnitType unitType, Tile tile){
        super(unitType,tile, civilization);
    }

    public int getLastActionTurn() {
        return lastActionTurn;
    }
    private int attackPenalty(Combatble target) {
        if (this.getType() == UnitType.Tank && target instanceof City)
            return -10;
        if (this.getType() == UnitType.Artillery && target instanceof City)
            return 10;
        if (this.getType() == UnitType.AntiTankGun && ((MilitaryUnit) target).getType() == UnitType.Tank)
            return 10;
        if (this.getType() == UnitType.Canon && target instanceof City)
            return 10;
        if (this.getType() == UnitType.Trebuchet && target instanceof City)
            return 10;
        if (this.getType() == UnitType.Pikeman && ((MilitaryUnit) target).getType().getCombatType() == CombatType.MOUNTED)
            return 10;
        if (this.getType() == UnitType.CATAPULT && target instanceof City)
            return 10;
        if (this.getType() == UnitType.SPEARMAN && ((MilitaryUnit) target).getType().getCombatType() == CombatType.MOUNTED)
            return 10;
        return 0;
    }

    private void attack(MilitaryUnit militaryUnit) {
        if (getType().getRangedCombatStrength() == 0)
            militaryUnit.setHP(militaryUnit.getHp() - getType().getCombatStrength()
                    * getTile().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(militaryUnit));
        else
            militaryUnit.setHP(militaryUnit.getHp() - getType().getRangedCombatStrength()
                    * getTile().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(militaryUnit));
        militaryUnit.defend(this);
        if (this.getHp() > 0)
            if (militaryUnit.getHp() <= 0)
                militaryUnit.delete();

    }

    public void defend(Combatble target) {
        if (target instanceof MilitaryUnit) {
            MilitaryUnit targetMilitaryUnit = (MilitaryUnit) target;
            if (targetMilitaryUnit.getType().getRangedCombatStrength() == 0)
                targetMilitaryUnit.setHP(targetMilitaryUnit.getHp() - getType().getCombatStrength()
                        * getTile().getCombatModifier());
            if (targetMilitaryUnit.getHp() <= 0)
                targetMilitaryUnit.delete();
        } else {
            System.err.println("tarafe moghabel military va city nist");
            throw new RuntimeException();
        }
    }

    public boolean isOnGarrison() {
        return isOnGarrison;
    }

    public void setFortifyHeal(boolean fortifyHeal) {
        isFortifyHeal = fortifyHeal;
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

    public void pillage() {
        if (getTile().getImprovementConnector().getFirst() == null
                && getTile().getImprovementConnector().getSecond())
            getTile().getImprovementConnector().setSecond(false);
        else
            throw new RuntimeException();
    }

    public void move(Tile tile) {
        super.move(tile);
//this is different than rest
    }

//    to do bellow as attacking in City class
    public void deleteUnit() {
        super.deleteUnit();
    }
}