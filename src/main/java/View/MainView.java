package View;

import Controller.MenuController.MainViewController;
import View.Regexes.MainMenuRegex;

public class MainView {
    private MainViewController mainViewController = new MainViewController();

    public void run() {
        while (Menu.getMenu() == Menu.MAIN) {
            String cmd = Menu.input();
            if (Menu.checkMatching(MainMenuRegex.Exit, cmd)) {
                System.out.println(mainViewController.exit());
            }
            else if (Menu.checkMatching(MainMenuRegex.showCurrentMenu, cmd)) {
                System.out.println(mainViewController.showCurrentMenu());
            }
            else if (Menu.checkMatching(MainMenuRegex.logout, cmd)) {
                System.out.println(mainViewController.Logout());
            }
            else if (Menu.checkMatching(MainMenuRegex.goToMenu, cmd)) {
                System.out.println(mainViewController.goToNextMenu(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(MainMenuRegex.playGame, cmd)) {
                System.out.println(mainViewController.PlayGame(Menu.getMatcher()));
            }
            else {
                System.out.println(mainViewController.invalidMessage());
            }
        }
    }
}
