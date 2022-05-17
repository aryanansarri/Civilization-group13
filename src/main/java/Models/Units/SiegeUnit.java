package Models.Units;

import Models.Block.Tile;
import Models.Civilizations.Civilization;

public class SiegeUnit extends MilitaryUnit {
    public SiegeUnit(Civilization civilization, UnitType unitType, Tile tile) {
        super(civilization, unitType, tile);
    }
    private boolean SiegeMode;
    public void setUp() {
        SiegeMode = true;
        setDutyCompleted(true);
    }

    public boolean isInSiege() {
        return SiegeMode;
    }

    public void setInSiege(boolean inSiege) {
        SiegeMode = inSiege;
    }

}