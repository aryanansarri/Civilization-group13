package Controller.GameController;

import Models.Block.Tile;
import Models.Civilizations.City;
import Models.Civilizations.Civilization;
import Models.Coordinates;
import Models.Units.CivilianUnit;
import Models.Units.Unit;
import View.Menu;

import java.util.regex.Matcher;

public class GameViewController {

    private InfoController infoController = new InfoController();
    private CityController cityController = new CityController();
    private CheatController cheatController = new CheatController();
    private MapController mapController = new MapController();
    private TechnologyMenuController technologyMenuController = new TechnologyMenuController();
    public TechnologyMenuController getTechnologyMenuController() {
        return technologyMenuController;
    }

    public String exitGameMenu() {
        Menu.goToMenu(Menu.MAIN);
        return "You are taken to the Main Menu";
    }

    public String showCurrentMenu() {
        return "Game Menu";
    }

    public String goToNextMenu() {
        return "menu navigation is not possible";
    }

    public String nextTurn() {
        for (Unit unit : GameDatabase.getGameDatabase().getCurrentCivilization().getUnits()) {
            if (!unit.isWorkDone()) {
                return "a unit didn't work done yet";
            }
        }
        GameDatabase.getGameDatabase().nextTurn();
        Civilization curr = GameDatabase.getGameDatabase().getCurrentCivilization();
        for (City city : curr.getCities()) {
            city.nextTurn();
        }
        curr.nextTurn();
        return "next turn was done!";
    }

    public String invalidMessage() {
        return "invalid command";
    }

    public InfoController getInfoController() {
        return infoController;
    }

    public CityController getCityController() {
        return cityController;
    }
    public String selectCityInCoordination(Matcher matcher) {
        Coordinates coordinates = new Coordinates(Integer.parseInt(matcher.group("x")),
                                                                Integer.parseInt(matcher.group("y")));
        if (!coordinates.isValidCoordination(GameDatabase.getGameDatabase().getOriginalMap())) {
            return "coordinates with x: " + coordinates.getX() + " and y: " + coordinates.getY() +
                    " is not valid";
        }
        City city = GameDatabase.getGameDatabase().getCityCoordinates(coordinates);
        if (city == null) {
            return "not find city in given coordinates";
        }
        if (GameDatabase.getGameDatabase().getCivilizationByCity(city) != GameDatabase.getGameDatabase().getCurrentCivilization()) {
            return "its not your civilization!";
        }
        GameDatabase.getGameDatabase().setSelected(city);
        return "city is selected successfully";
    }
    public String selectCivilizationUnits(Matcher matcher) {
        Coordinates coordinates = new Coordinates(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        Tile tile = GameDatabase.getGameDatabase().getOriginalMap().getTile(coordinates.getX(), coordinates.getY());
        if (tile == null) {
            return "not found any unit there";
        }
        GameDatabase.getGameDatabase().setSelected(tile);
        return "unit selected successfully";
    }

    public CheatController getCheatController() {
        return cheatController;
    }

    public MapController getMapController() {
        return mapController;
    }
}
