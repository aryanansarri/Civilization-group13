package Controller.GameController;

import Models.Civilization.City;
import Models.Coordinates;
import View.GameView;
import View.Menu;

import java.util.regex.Matcher;

public class GameViewController {

    private InfoController infoController = new InfoController();
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
//        to do
        return "next turn was done!";
    }

    public String invalidMessage() {
        return "invalid command";
    }

    public InfoController getInfoController() {
        return infoController;
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
}
