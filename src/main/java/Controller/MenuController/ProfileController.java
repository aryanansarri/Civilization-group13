package Controller.MenuController;

import Controller.PlayerDatabase;
import View.Menu;
import View.ProfileMenu;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;

public class ProfileController {
    public String exitProfileMenu() {
        Menu.goToMenu(Menu.MAIN);
        return "You are taken to the Main Menu";
    }

    public String showCurrentMenu() {
        return "Profile Menu";
    }

    public String goToNextMenu() {
        return "menu navigation is not possible";
    }

    public String changeNickname(Matcher matcher) {
        String newNickname = matcher.group(7);
        if (PlayerDatabase.getPlayerDatabase().getUserByNickname(newNickname) != null) {
            return "user with nickname " + newNickname + " already exists";
        }
        PlayerDatabase.getPlayerDatabase().getLoggedInUser().setNickname(newNickname);
        return "nickname changed successfully!";
    }

    public String changePassword(Matcher matcher) {
        String currentPassword = "";
        String newPassword = "";
        String first = matcher.group(7);
        String second = matcher.group(11);

        if (first.equals("--current") || first.equals("-c")) {
            currentPassword = matcher.group(9);
        }
        else if (second.equals("--current") || second.equals("-c")) {
            currentPassword = matcher.group(13);
        }

        if (first.equals("--new") || first.equals("-n")) {
            newPassword = matcher.group(9);
        }
        else if (second.equals("--new") || second.equals("-n")) {
            newPassword = matcher.group(13);
        }

        if (currentPassword.equals("") || newPassword.equals("")) {
            return invalidMessage();
        }

        if (!PlayerDatabase.getPlayerDatabase().getLoggedInUser().getPassword().equals(currentPassword)) {
            return "current password is invalid";
        }

        if (currentPassword.equals(newPassword)) {
            return "please enter a new password";
        }

        PlayerDatabase.getPlayerDatabase().getLoggedInUser().setPassword(newPassword);
        return "password changed successfully!";
    }

    public String invalidMessage() {
        return "invalid command";
    }
}
