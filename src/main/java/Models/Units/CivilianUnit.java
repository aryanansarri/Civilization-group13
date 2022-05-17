package Models.Units;

import Models.Block.Tile;
import Models.Civilizations.Civilization;

public class CivilianUnit extends Unit{
    CivilianUnit(Civilization civilization, UnitType unitType, Tile tile){
        super(unitType,tile, civilization);
    }
}