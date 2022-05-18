package Models.Units;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainType;
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


    public void foundCity() {
        for (City city : getCivilization().getCities()) {
            if (city.isBeingcapital()) {
                foundNormalCity();
                return;
            }
        }
        foundCapital();
    }

    private void foundNormalCity() {
        City city = new City();
        city.setBeingcapital(false);
        city.setCivilization(getCivilization(),getTile());
        GameDatabase.getGameDatabase().getOriginalMap().setTile(getTile().getX(), getTile().getY(), getTile());
        for (Tile tile : city.getSurroundingTiles()) {
            tile.setOwner(getCivilization());
        }
        if (getTile().getTerraintype() == TerrainType.HILLS) city.setHealth(city.getHealth() + 20);
        delete();
    }
    private void foundCapital() {
        City city = new City(getTile().getCity());
        city.setBeingcapital(true);
        city.setCivilization(getCivilization(),getTile());
        GameDatabase.getGameDatabase().getOriginalMap().setTile(getTile().getX(), getTile().getY(), getTile());
        for (Tile terrain : city.getSurroundingTiles()) {
            terrain.setOwner(getCivilization());
        }
        if (getTile().getTerraintype() == TerrainType.HILLS)
            city.setHealth(city.getHealth() + 20);
        delete();
    }
}
