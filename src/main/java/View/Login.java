package View;

import Controller.LoginController;
import View.Regexes.LoginRegex;

import java.util.regex.Matcher;

public class Login {
    private LoginController loginController = new LoginController();
    public void run() {
        while (Menu.getMenu() == Menu.LOGIN) {
            Matcher m;
            String cmd = Menu.input();
            if ((Menu.getCommand(LoginRegex.EXIT, cmd)) != null) {
                System.out.println(loginController.exitGame());
            }
            else if ((m = Menu.getCommand(LoginRegex.Register, cmd)) != null) {
                System.out.println(loginController.RegisterToGame(m));
            }
            else if ((m = Menu.getCommand(LoginRegex.Abbreviation_Register, cmd)) != null) {
                System.out.println(loginController.RegisterToGame(m));
            }
            else if ((m = Menu.getCommand(LoginRegex.LOGIN, cmd)) != null) {
                System.out.println(loginController.LoginToGame(m));
            }
            else if ((m = Menu.getCommand(LoginRegex.Abbreviation_login, cmd)) != null) {
                System.out.println(loginController.LoginToGame(m));
            }
            else if ((Menu.getCommand(LoginRegex.showCurrentMenu, cmd)) != null) {
                System.out.println(loginController.showCurrentMenu());
            }
            else {
                System.out.println(loginController.invalidMassage());
            }
        }
    }
}
