package View;

import Controller.GameController.CityController;
import Controller.GameController.GameDatabase;
import Controller.GameController.GameViewController;
import Controller.GameController.InfoController;
import Models.Civilization.City;
import Models.Civilization.Civilization;
import Models.Coordinates;
import View.Regexes.GameRegex;
import View.Regexes.InfoGameRegex;
import View.Regexes.SelectCityRegex;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;

public class GameView {
    private GameViewController gameViewController = new GameViewController();
    private CityController cityController = new CityController();

    public void run() {
        while (Menu.getMenu() == Menu.GAME) {
            String cmd = Menu.input();
            if (Menu.checkMatching(GameRegex.Exit, cmd)) {
                System.out.println(gameViewController.exitGameMenu());
            }
            else if (Menu.checkMatching(GameRegex.showCurrentMenu, cmd)) {
                System.out.println(gameViewController.showCurrentMenu());
            }
            else if (Menu.checkMatching(GameRegex.goToMenu, cmd)) {
                System.out.println(gameViewController.goToNextMenu());
            }
            else if (Menu.checkMatching(GameRegex.showMap, cmd)) {
                showMap(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.showInfo, cmd)) {
               showInfo(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectCityByCoordinate, cmd)) {
                selectCity(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectCityByCoordinateType2, cmd)) {
                selectCity(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectMilitaryUnit, cmd)) {
                selectMilitaryUnit(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectSettler, cmd)) {
               selectSettler(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectWorker, cmd)) {
                selectWorker(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.technologyMenu, cmd)) {
                technologyMenu(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.cheatMenu, cmd)) {
                cheatMenu(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.nextTurn, cmd)) {
                System.out.println(gameViewController.nextTurn());
            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void showMap(Matcher matcher) {
//        to do
    }

    private void showInfo(Matcher matcher) {
        Civilization current = GameDatabase.getGameDatabase().getCurrentCivilization();
        current.Refresh();
        String State = "Info";
        System.out.println(current);
        InfoController infoController = getGameViewController().getInfoController();
        while (State.equals("Info")) {
            String cmd = Menu.input();
            if (Menu.checkMatching(InfoGameRegex.research, cmd)) {
                System.out.println(infoController.getExploreInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.units, cmd)) {
                System.out.println(infoController.getUnitsListPanel());
            }
            else if (Menu.checkMatching(InfoGameRegex.cities, cmd)) {
                System.out.println(infoController.getCitiesListPanel());
            }
            else if (Menu.checkMatching(InfoGameRegex.diplomacy, cmd)) {
                System.out.println(infoController.getDiplomacyInformationPanel());
            }
            else if (Menu.checkMatching(InfoGameRegex.victory, cmd)) {
                System.out.println(infoController.getVictoryProcess());
            }
            else if (Menu.checkMatching(InfoGameRegex.demographic, cmd)) {
                System.out.println(infoController.getDemographics());
            }
            else if (Menu.checkMatching(InfoGameRegex.military, cmd)) {
                System.out.println(infoController.getMilitary());
            }
            else if (Menu.checkMatching(InfoGameRegex.notification, cmd)) {
                System.out.println(infoController.getNotification());
            }
            else if (Menu.checkMatching(InfoGameRegex.diplomatic, cmd)) {
                System.out.println(infoController.getDiplomatics());
            }
            else if (Menu.checkMatching(InfoGameRegex.economic, cmd)) {
                System.out.println(infoController.getEconomy());
            }
            else if (Menu.checkMatching(InfoGameRegex.deal, cmd)) {
                System.out.println(infoController.getDeal());
            }
            else if (Menu.checkMatching(InfoGameRegex.back, cmd)) {
                State = "back";
                System.out.println("you are taken to game menu");
            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void selectCity(Matcher matcher) {
        String selectedResult = gameViewController.selectCityInCoordination(matcher);
        System.out.println(selectedResult);
        if (!selectedResult.equals("city is selected successfully")) return;
        String state = "select";
        Coordinates coordinates = new Coordinates(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        City selectedCity = GameDatabase.getGameDatabase().getCityCoordinates(coordinates);
        while (state.equals("select")) {
            String cmd = Menu.input();
            if (Menu.checkMatching(SelectCityRegex.showCityInfo, cmd)) {
                System.out.println(cityController.showCityInfo(selectedCity));
            }
            else if ()
        }
    }

    private void selectMilitaryUnit(Matcher matcher) {
//        to do
    }

    private void selectSettler(Matcher matcher) {
//        to do
    }

    private void selectWorker(Matcher matcher) {
//        to do
    }

    private void technologyMenu(Matcher matcher) {
//        to do
    }

    private void cheatMenu(Matcher matcher) {
//        to do
    }

    public GameViewController getGameViewController() {
        return gameViewController;
    }
}
