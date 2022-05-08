package View;

import Controller.MenuController.LoginController;
import View.Regexes.LoginRegex;

public class Login {
    private LoginController loginController = new LoginController();
    public void run() {
        while (Menu.getMenu() == Menu.LOGIN) {
            String cmd = Menu.input();
            if (Menu.checkMatching(LoginRegex.EXIT, cmd)) {
                System.out.println(loginController.exitGame());
            }
            else if (Menu.checkMatching(LoginRegex.Register, cmd)) {
                System.out.println(loginController.RegisterToGame(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(LoginRegex.Abbreviation_Register, cmd)) {
                System.out.println(loginController.RegisterToGame(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(LoginRegex.LOGIN, cmd)) {
                System.out.println(loginController.LoginToGame(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(LoginRegex.Abbreviation_login, cmd)) {
                System.out.println(loginController.LoginToGame(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(LoginRegex.showCurrentMenu, cmd)) {
                System.out.println(loginController.showCurrentMenu());
            }
            else if (Menu.checkMatching(LoginRegex.goToMenu, cmd)) {
                System.out.println(loginController.goToMenu(Menu.getMatcher()));
            }
            else {
                System.out.println(loginController.invalidMassage());
            }
        }
    }
}
