package View;

import Controller.LoginController;
import View.Regexes.LoginRegex;

import java.util.regex.Matcher;

public class Login {
    private LoginController loginController = new LoginController();
    public void run() {
        do {
            Matcher m;
            String cmd = Menu.input();
            if ((Menu.getCommand(LoginRegex.EXIT, cmd)) != null) {
                System.out.println(loginController.exitGame());
            }
            else if ((m = Menu.getCommand(LoginRegex.LOGIN, cmd)) != null) {
                System.out.println(loginController.LoginToGame(m));
            }
            else {
                System.out.println("invalid Command");
            }
        }while (Menu.getMenu() == Menu.LOGIN);
    }
}
