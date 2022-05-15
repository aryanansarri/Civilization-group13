package Models.Units;


import Models.Block.Tile;
import Models.Civilization.City;
import Models.Civilization.Civilization;

public class MilitaryUnit extends Unit {
    private int XP;
    private int RANGE;
    private int lastActionTurn;
    private UnitType unitType;

    public MilitaryUnit(UnitType type, Tile tile, Civilization civilization) {
        super(type, tile, civilization);

    }

    public UnitType getUnitType() {
        return unitType;
    }

    private void attack(City city) {
        if (getUnitType().getRangedCombatStrength() == 0)
            city.setHealth(city.getHealth() - getUnitType().getCombatStrength() * getTile().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(city));
        else
            city.setHappiness(city.getHealth() - getUnitType().getRangedCombatStrength() * getTile().getCombatModifier() * UnitType.getDefensiveBonus(this) + attackPenalty(city));
        city.defend(this);
        if (this.getHp() > 0) if (city.getHealth() <= 0) city.setOwnership(this.getCivilization());
    }

    @Override
    public void setHP(int hp) {
        super.setHP(hp);
    }

    public void defend(Combatble target) {
        if (target instanceof MilitaryUnit) {
            MilitaryUnit targetMilitaryUnit = (MilitaryUnit) target;
            if (targetMilitaryUnit.getUnitType().getRangedCombatStrength() == 0)
                targetMilitaryUnit.setHP(targetMilitaryUnit.getHp() - getUnitType().getCombatStrength() * getTile().getCombatModifier());
            if (targetMilitaryUnit.getHp() <= 0) targetMilitaryUnit.delete();
        } else {
            System.out.println("Ain't no valid defence");
        }
    }

    public int getLastActionTurn() {
        return lastActionTurn;
    }

    public boolean isOnGarrison() {
        return isOnGarrison;
    }

    public double getCombatStrength(int thisTurn) {
        double strength = this.getCombatStrength();
        if (this.isOnFortify) {
            for (int i = lastActionTurn; i < thisTurn; i++) {
                strength = 1.25 * getCombatStrength();
            }
        } else if (this.isOnGarrison) {
            for (int i = lastActionTurn; i < thisTurn; i++) {
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

    private int attackPenalty(Combatble target) {
        if (this.getUnitType() == UnitType.Trebuchet && target instanceof City) return 10;
        if (this.getUnitType() == UnitType.SPEARMAN && ((MilitaryUnit) target).getUnitType().getCombatType() == CombatType.MOUNTED)
            return 10;
        if (this.getUnitType() == UnitType.Artillery && ((MilitaryUnit) target).getUnitType() == UnitType.Tank)
            return 10;
        if (this.getUnitType() == UnitType.Pikeman && ((MilitaryUnit) target).getUnitType().getCombatType() == CombatType.MOUNTED)
            return 10;
        if (this.getUnitType() == UnitType.Tank && target instanceof City) return -10;
        if (this.getUnitType() == UnitType.Artillery && target instanceof City) return 10;
        if (this.getUnitType() == UnitType.Canon && target instanceof City) return 10;
        if (this.getUnitType() == UnitType.CATAPULT && target instanceof City) return 10;
        return 0;
    }
}