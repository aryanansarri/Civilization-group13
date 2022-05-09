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
            else if (Menu.checkMatching(GameRegex.SHOW_MAP, cmd)) {
                showMap(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.SHOW_INFO, cmd)) {
               showInfo(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.SELECT_CITY_BY_COORDINATE, cmd)) {
                selectCity(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.SELECT_CITY_BY_COORDINATE_yFirst, cmd)) {
                selectCity(Menu.getMatcher());
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
}
