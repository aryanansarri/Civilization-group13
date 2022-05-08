package Controller.MenuController;

import Controller.PlayerDatabase;
import Controller.User;
import View.Menu;

import java.util.ArrayList;
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
//        if (menu.equals("Game Menu"))
//        {
//            Menu.goToMenu(Menu.GAME);
//            return "go to Game Menu";
//        }
        return invalidMessage();
    }

    public String PlayGame(Matcher matcher) {
        String[] subCommands = matcher.group("command").split(" --player\\d+ ");
        ArrayList<User> players = new ArrayList<>();
        for (int i = 1; i < subCommands.length; i++) {
            User player = PlayerDatabase.getPlayerDatabase().getUser(subCommands[i]);
            if (player == null){
                return "player " + subCommands[i] + " was not exist!";

            }
        }
        if (players.size() > 1) {
            // to do
            return "Game started successfully!";
        }
        return invalidMessage();
    }

    public String invalidMessage() {
        return "invalid command";
    }
}
