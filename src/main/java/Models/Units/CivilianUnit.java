package Models.Units;

import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.Civilization;

public class CivilianUnit extends Unit{
    CivilianUnit(Civilization civilization, UnitType unitType, Tile tile){
        super(unitType,tile, civilization);
    }
}