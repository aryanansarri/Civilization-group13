package Controller;

import View.Menu;

import java.util.regex.Matcher;

public class MainViewController {
    public String exit() {
        Menu.goToMenu(Menu.LOGIN);
        return "You are taken to the Login Menu";
    }

    public String showCurrentMenu() {
        return "Main Menu";
    }

    public String Logout() {
        PlayerDatabase.getPlayerDatabase().Logout();
        Menu.goToMenu(Menu.LOGIN);
        return "user logged out successfully!";
    }

    public String goToNextMenu(Matcher matcher) {
        String menu = matcher.group(5);
        if (menu.equals("Profile Menu")) {
            Menu.goToMenu(Menu.PROFILE);
            return "go to Profile Menu";
        }
        if (menu.equals("Game Menu"))
        {
            Menu.goToMenu(Menu.GAME);
            return "go to Game Menu";
        }
        return invalidMessage();
    }

    public String invalidMessage() {
        return "invalid command";
    }
}
