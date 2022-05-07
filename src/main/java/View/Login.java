package View;

import Controller.LoginController;
import View.Regexes.LoginRegex;

import java.util.regex.Matcher;

public class Login {
    private LoginController loginController = new LoginController();
    public void run() {
        do {
            Matcher m;
            if ((Menu.getCommand(LoginRegex.EXIT)) != null) {
                System.out.println(loginController.exitGame());
            }
            else if ((m = Menu.getCommand(LoginRegex.LOGIN)) != null) {
                System.out.println(loginController.LoginToGame(m));
            }
            else if (false) {
//                   to do
            }
            else {
                System.out.println("invalid Command");
            }
        }while (Menu.getMenu() == Menu.LOGIN);
    }
}
