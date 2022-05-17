package Models.Units;

import Models.Block.Tile;
import Models.Civilizations.City;
import Models.Civilizations.Civilization;

public class SettlerUnit extends CivilianUnit {
    public SettlerUnit(Civilization civilization, UnitType unitType, Tile tile){
        super(civilization,unitType,tile);
    }
    public void searchForCity(Tile tile) {
        City city = new City(tile.getGold(),tile.getFood(),tile);
    }
}