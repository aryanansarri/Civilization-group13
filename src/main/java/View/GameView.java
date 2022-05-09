package View;

import Controller.GameController.GameViewController;
import View.Regexes.GameRegex;
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
//        to do
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
}
