package Models.Units;

import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.City;
import Models.Civilization.Civilization;

public class SettlerUnit extends CivilianUnit {
    public SettlerUnit(Civilization civilization, UnitType unitType, Tile tile){
        super(civilization,unitType,tile);
    }
    public void searchForCity(Tile tile) {
        City city = new City(tile.getGold(),tile.getFood(),tile);
    }
}