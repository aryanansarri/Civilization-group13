package Controller;

import View.Menu;

import java.util.regex.Matcher;

public class LoginController {
    public String exitGame() {
        Menu.goToMenu(Menu.E);
        return "You have successfully exited the game!";
    }

    public String LoginToGame(Matcher matcher) {
        return "";
    }
}
