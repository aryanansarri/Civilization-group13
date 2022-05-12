package View;

import Controller.GameController.GameDatabase;
import Controller.GameController.GameViewController;
import Models.Civilization.Civilization;
import View.Regexes.GameRegex;
import View.Regexes.InfoGameRegex;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;

public class GameView {
    private GameViewController gameViewController = new GameViewController();

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
        while (State.equals("Info")) {
            String cmd = Menu.input();
            if (Menu.checkMatching(InfoGameRegex.research, cmd)) {
                System.out.println(gameViewController.researchInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.units, cmd)) {
                System.out.println(gameViewController.unitsInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.cities, cmd)) {
                System.out.println(gameViewController.citiesInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.diplomacy, cmd)) {
                System.out.println(gameViewController.diplomacyInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.victory, cmd)) {
                System.out.println(gameViewController.victoryInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.demographic, cmd)) {
                System.out.println(gameViewController.demographicInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.military, cmd)) {
                System.out.println(gameViewController.militaryInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.notification, cmd)) {
                System.out.println(gameViewController.notification());
            }
            else if (Menu.checkMatching(InfoGameRegex.diplomatic, cmd)) {
                System.out.println(gameViewController.diplomaticInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.economic, cmd)) {
                System.out.println(gameViewController.economicInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.deal, cmd)) {
                System.out.println(gameViewController.dealInformation());
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
//        to do
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
