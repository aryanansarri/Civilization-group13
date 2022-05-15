package Controller.GameController;

import Controller.User;
import Models.Improvment.Improvement;
import Models.Technology.Technology;
import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.City;
import Models.Civilization.Civilization;
import Models.Info.CivilizationScience;
import Models.Resources.Resource;
import View.Menu;

import java.util.regex.Matcher;

public class CheatController {
    public void turnCheat(User user, int amount) {
        user.setGold(user.getGold() + amount);
    }

    public void goldCheat(User user, int amount) {
        user.setTurns(user.getTurns() + amount);
    }

    public void addTech(Civilization civilization, Technology technology) {
        civilization.getCivilizationTechnology().getPassedTechnology().add(technology);
    }

    public void addImprovement(Tile tile, Improvement improvement) {
        tile.setImprovement(improvement, );
    }

    public void changeTerrainFeature(Tile tile, TerrainFeature terrainFeature) {
        tile.setFeature(terrainFeature);
    }

    public void changeTerrainType(Tile tile, TerrainType terrainType) {
        tile.setTerraintype(terrainType);
    }

    public void addFood(City city, int amount) {
        city.setFood(city.getFood() + amount);
    }

    public void addHappiness(City city, int amount) {
        city.setHappiness(city.getHappiness() + amount);
    }

    public void addResource(Tile tile, Resource resource) {
        tile.getResources().add(resource);
    }

    public void changeOwner(Tile tile, Civilization civilization) {
        tile.setOwner(civilization);
    }
}